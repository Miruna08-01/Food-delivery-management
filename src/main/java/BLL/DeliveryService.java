package BLL;

import DAO.*;
import DAO.FileWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeliveryService extends Observable implements DeliveryServiceProcessing {
    private TreeMap<Order, ArrayList<MenuItem>> orders ;
    private ArrayList<MenuItem> items ;
    private HashSet<MenuItem> menuitems ;
    private ArrayList<Client> clients ;
    private ArrayList<Employee> employees ;
    private ArrayList<Admin> admins ;
    private ArrayList<CompositeProduct> composites;
    private HashSet<Order> totalOrders ;
    private Serialize sr = new Serialize();
    DAO.FileWriter wr = new DAO.FileWriter();

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public DeliveryService() throws IOException {
        //this.orders=new TreeMap<>();
//        this.menuitems=new HashSet<>();
    //this.totalOrders=new HashSet<>();
//         this.clients=new ArrayList<>();
//        readFromFile();
//        this.admins=new ArrayList<>();
//        readFromFileAdmin();
//        this.composites=new ArrayList<>();
//        this.employees=new ArrayList<>();
//        readFromFileEmployee();
        this.orders= (TreeMap<Order, ArrayList<MenuItem>>) sr.deserializeMap(orders, "products.ser");
        this.menuitems = (HashSet<MenuItem>) sr.deserializeCollection(menuitems, "DeliveryService.ser");
        this.totalOrders = (HashSet<Order>) sr.deserializeCollection(totalOrders, "orders.ser");
        this.clients= (ArrayList<Client>) sr.deserializeCollection(clients,"Clients.ser");
        this.composites= (ArrayList<CompositeProduct>) sr.deserializeCollection(composites,"Composite.ser");
        this.admins= (ArrayList<Admin>) sr.deserializeCollection(admins,"Admins");
        this.employees= (ArrayList<Employee>) sr.deserializeCollection(employees,"Employees");
    }
        public void readFromFile() throws IOException {
        File file=new File("Clienti");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String st="";
        while((st= br.readLine())!=null){
            String[] s=st.split(" ");
            this.clients.add(new Client((Integer.parseInt(s[0])),s[1],s[2]));
        }
            sr.serializeCollection(clients,"Clients.ser");
        }

    public void readFromFileAdmin() throws IOException {
        File file=new File("Admin");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String st="";
        while((st= br.readLine())!=null){
            String[] s=st.split(" ");
            this.admins.add(new Admin(s[0],s[1]));
        }
        sr.serializeCollection(admins,"Admins");
    }

    public void readFromFileEmployee() throws IOException {
        File file=new File("Employee");
        BufferedReader br=new BufferedReader(new FileReader(file));
        String st="";
        while((st= br.readLine())!=null){
            String[] s=st.split(" ");
            this.employees.add(new Employee(s[0],s[1]));
        }
        sr.serializeCollection(employees,"Employees");
    }

    /**
     * @param name
     */
    @Override
    public void deleteBaseProduct(String name) {
        assert name != null;
        menuitems.removeIf(r -> r.getTitle().equals(name));
        assert wellFormed();

        sr.serializeCollection(menuitems, "DeliveryService.ser");
    }

    /** Modificarea unui produs
     * @pre item!=null
     * @pre item.price>0 sa fim siguri ca nu punem pretul mai mic ca 0
     * @param item
     * @post wellFormed();
     */
    @Override
    public void updateBaseProduct(MenuItem item) {
        assert item != null;
        assert item.getPrice() > 0;
        menuitems.removeIf(l -> l.getTitle().contentEquals(item.getTitle()));

        menuitems.add(item);
        assert wellFormed();
        sr.serializeCollection(menuitems, "DeliveryService.ser");
    }

    /** Metoda pentru adaugarea unui produs nou in sistem
     * @pre wellFormed();
     * @param item
     * @post wellFormed();
     */
    @Override
    public void addMenuItem(BaseProduct item) {
        assert wellFormed();
        menuitems.add(item);
        sr.serializeCollection(menuitems, "DeliveryService.ser");
        assert wellFormed();

    }

    /** Metoda pentru adaugarea unui produs compus in sistem
     * @pre wellFormed();
     * @param composite produsul compus
     * @param name numele produsului
     * @post wellFormed();
     */
    @Override
    public void createMenuItem(ArrayList<MenuItem> composite, String name) {
        assert wellFormed();
        MenuItem item = new CompositeProduct(composite, name);
        menuitems.add(item);
        composites.add((CompositeProduct) item);
        sr.serializeCollection(menuitems, "DeliveryService.ser");
        sr.serializeCollection(composite,"Composite.ser");
        assert wellFormed();
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    /**
     * Metoda pentru importarea produselor din products.csv
     */
    @Override
    public void importProducts() {
        List<List<String>> products = new ArrayList<>();;
        try (Stream<String> stream = Files.lines(Paths.get("products.csv")).skip(1)) {
            products = stream.map(string -> Arrays.asList(string.split(","))).collect(Collectors.toList());
            for (List<String> i : products) {

                MenuItem br = new BaseProduct(i.get(0), Float.parseFloat(i.get(1)), Integer.parseInt(i.get(2)), Integer.parseInt(i.get(3)), Integer.parseInt(i.get(4)), Integer.parseInt(i.get(5)), Float.parseFloat(i.get(6)));
                if (menuitems.contains(br)) {
                    continue;
                } else
                    this.menuitems.add(br);
            }
            sr.serializeCollection(menuitems, "DeliveryService.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /** Metoda pentru crearea unei noi comenzi
     * @param clientId
     * @param orderId
     * @param date
     * @param number
     * @param items
     * @param totalPrice
     */
    @Override
    public void createOrder(int clientId, int orderId, LocalDateTime date, int number, ArrayList<MenuItem> items, double totalPrice) {
        for (int i = 0; i < number; i++) {
            totalPrice += items.get(i).getPrice();
        }
        Order newOrder = new Order(clientId, orderId, date);
        newOrder.setTotalPrice(totalPrice);
        orders.put(newOrder, items);
        Map<Order,ArrayList<MenuItem>>ord=new HashMap<>();
        wr.generateBill(newOrder, items);
        totalOrders.add(newOrder);
        ord.put(newOrder,items);
        sr.serializeCollection(totalOrders, "orders.ser");
        sr.serializeMap(orders, "products.ser");
        setChanged();
        notifyObservers(newOrder);

    }


    /** Metoda de generare a unui raport intr-un interval de timp
     * @param start  ora de inceput
     * @param finish ora de sfarsit
     * @return
     */
    @Override
    public List<Order> generateReportInterval(int start, int finish) {
        List<Order> ord = totalOrders.stream().filter(d -> d.getOrderDate().getHour() >= start && d.getOrderDate().getHour() < finish).collect(Collectors.toList());
        wr.generateTimeInterval(ord);
        return ord;
    }

    public Map<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, ArrayList<MenuItem>> orders) {

        this.orders = (TreeMap<Order, ArrayList<MenuItem>>) orders;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public HashSet<Order> getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(HashSet<Order> totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Serialize getSr() {
        return sr;
    }

    public void setSr(Serialize sr) {
        this.sr = sr;
    }

    /** Metoda ce generaza un raport dupa zi
     * @param date  Data in care se face raportul
     * @return
     */
    @Override
    public List<MenuItem> generateReportDay(LocalDate date) {
        Set<Map.Entry<Order, ArrayList<MenuItem>>> en = orders.entrySet();
        List<MenuItem> products = new ArrayList<>();
        List<List<MenuItem>> p = en.stream().filter(d -> d.getKey().getOrderDate().getDayOfMonth() == date.getDayOfMonth() && d.getKey().getOrderDate().getMonthValue() == date.getMonthValue()).map(o -> o.getValue()).collect(Collectors.toList());
        for (int i = 0; i < p.size(); i++) {
            for (MenuItem m : p.get(i)) {
                products.add(m);
            }
        }
        wr.generateDay(products);
        return products;
    }

    /**Metoda pentru generarea clientilor care au cumparat de times ori si suma a fost mai mare decat amount
     * @param times numarul de cumparari
     * @param amount suma
     * @return
     */
    @Override
    public HashSet<Client> generateLoyalClients(int times, int amount) {
        Set<Map.Entry<Order, ArrayList<MenuItem>>> en = orders.entrySet();
        List<Integer> clientss = en.stream().filter(a -> a.getKey().getTotalPrice() >= amount).map(d -> d.getKey()).map(c -> c.getClientId()).collect(Collectors.toList());
        List<Integer> c = clientss.stream().filter(f -> (Collections.frequency(clientss, f) >= times)).collect(Collectors.toList());
        HashSet<Client> clientsGenerated = new HashSet<>();
        for (Client cl : clients) {
            for (Integer i : c) {
                if (cl.getId() == i) {
                    clientsGenerated.add(cl);
                }
            }
        }
        wr.generateLoyalClients(clientsGenerated);
        return clientsGenerated;
    }

    /** Metoda de calculare a produselor care au fost cumparate de cel putin rating ori
     * @param rating
     * @return
     */
    @Override
    public List<MenuItem> generateMostLikedProducts(float rating) {
            List<MenuItem> popular = new ArrayList<>();
        List<MenuItem> items = new ArrayList<>();
            List<ArrayList<MenuItem>> list= orders.values().stream().toList();
            for(ArrayList<MenuItem> m:list){
                items.addAll(m);
            }
                popular=items.stream().filter(f-> (Collections.frequency(items,f)==rating)).collect(Collectors.toList());
       wr.generateLikedProd(popular);
        return popular;
    }

    /** Metoda care returneaza toate produsele ce contin name in  nume
     * @pre nume!=null
     * @param name numele produsului
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByName(String name) {

        assert name!=null;
        HashSet<MenuItem> foodByName = new HashSet<>();
        foodByName = menuitems.stream().filter(r -> r.getTitle().contains(name)).collect(Collectors.toCollection(HashSet::new));
        return foodByName;

    }

    /**Metoda care returneaza produsele ce au pretul egal cu price
     * @pre price>0
     * @param price
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByPrice(float price) {
        assert 0 < price;
        HashSet<MenuItem> foodByPrice = (HashSet<MenuItem>) menuitems.stream().filter(r -> r.getPrice() == price).collect(Collectors.toSet());
        return foodByPrice;


    }

    /**Metoda care returneaza produsele ce au ratingul egal cu rating
     * @pre 0<=rating<=5
     * @param rating
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByRating(float rating) {
        assert 0 <= rating;
        assert 5>=rating;
        HashSet<MenuItem> foodByRating = (HashSet<MenuItem>) menuitems.stream().filter(r -> r.getRating() == rating).collect(Collectors.toSet());
        return foodByRating;
    }


    @Override
    public HashSet<MenuItem> viewMenu() {
        return menuitems;

    }

    /**Metoda care returneaza produsele ce au proteinele egal cu proteins
     * @pre 0<=proteins
     * @param proteins
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByProteins(int proteins) {
        assert 0 <= proteins;
        HashSet<MenuItem> foodByProteins = (HashSet<MenuItem>) menuitems.stream().filter(r -> r.getProteins() == proteins).collect(Collectors.toSet());
        return foodByProteins;
    }

    /**Metoda care returneaza produsele ce au grasimile egal cu fat
     * @pre 0<=fat
     * @param fat numarul de grasimi
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByFat(int fat) {
        assert 0 <= fat;
        HashSet<MenuItem> foodByFat = (HashSet<MenuItem>)menuitems.stream().filter(r -> r.getFat() == fat).collect(Collectors.toSet());
        return foodByFat;
    }

    /**Metoda care returneaza produsele ce au caloriile egale cu calories
     * @pre 0<calories
     * @param calories
     * @return
     */
    @Override
    public HashSet<MenuItem> searchByCalories(int calories) {
        assert 0 <= calories;
        HashSet<MenuItem> foodByCal = (HashSet<MenuItem>) menuitems.stream().filter(r -> r.getCalories() == calories).collect(Collectors.toSet());
        return foodByCal;
    }

    /**Metoda care returneaza produsele ce au sodiul egal cu sodium
     * @pre 0<=sodium
     * @param sodium
     * @return
     */
    @Override
    public HashSet<MenuItem> searchBySodium(int sodium) {
        assert 0 <= sodium;
        HashSet<MenuItem> foodBySodium = (HashSet<MenuItem>) menuitems.stream().filter(r -> r.getSodium() == sodium).collect(Collectors.toSet());
        return foodBySodium;
    }

    /**Metoda ce adauga un client in sistem
     * @pre nume!=null
     * @pre password!=null
     * @param nume
     * @param password
     * @return
     */
    public int adaugaClient(String nume, String password) {
        assert nume!=null;
        assert password!=null;
        this.clients.add(new Client(this.clients.size()+1,nume,password));
        sr.serializeCollection(clients,"Clients.ser");
        return this.clients.size();
    }



    public boolean wellFormed() {
        ArrayList<Integer>orderId=new ArrayList<>();
        for (Order order : this.orders.keySet()) {
            if (orderId.contains(order.getOrderId())) {
                return  false;
            } else {
                orderId.add(order.getOrderId());
            }
        }
        for (MenuItem m : menuitems) {
            if (m instanceof CompositeProduct) {
                CompositeProduct composite = (CompositeProduct) m;
                for (MenuItem item : composite.getItems()) {
                    if (menuitems.contains(item)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public boolean validateClient(String username, String password) {

        for (int i = 0; i < this.getClients().size(); i++) {
            if (this.getClients().get(i).getName().equals(username) && this.getClients().get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateNewClient(String username) {
        for (int i = 0; i < this.getClients().size(); i++) {
            if (this.getClients().get(i).getName().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public boolean validateNewAdmin(String username,String password) {
        for (int i = 0; i < this.getAdmins().size(); i++) {
            if (this.getAdmins().get(i).getName().equals(username)) {
                return false;
            }
        }
        admins.add(new Admin(username,password));
        sr.serializeCollection(admins,"Admin");
        return true;
    }

    public boolean validateAdmin(String username, String password) {

        for (int i = 0; i < this.getAdmins().size(); i++) {
            if (this.getAdmins().get(i).getName().equals(username) && this.getAdmins().get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateNewEmployee(String username,String password) {
        for (int i = 0; i < this.getEmployees().size(); i++) {
            if (this.getEmployees().get(i).getName().equals(username)) {
                return false;
            }
        }
        employees.add(new Employee(username,password));
        sr.serializeCollection(employees,"Employee");
        return true;
    }
    public boolean validateEmployee(String username, String password) {

        for (int i = 0; i < this.getEmployees().size(); i++) {
            if (this.getEmployees().get(i).getName().equals(username) && this.getEmployees().get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public int getId(String username) {
        for (Client c : clients) {
            if (c.getName().equals(username)) {
                return c.getId();
            }
        }
        return -1;
    }

    public Client getClient(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public int getOrderNumber() throws IOException {
        return this.totalOrders.size();


    }


    public HashSet<MenuItem> getMenuitems() {
        return menuitems;
    }

    public void setMenuitems(HashSet<MenuItem> menuitems) {
        this.menuitems = menuitems;
    }

    public void newEmployee(String name, String password) {
        assert name != null && password != null;
        employees.add(new Employee(name, password));
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }
}
