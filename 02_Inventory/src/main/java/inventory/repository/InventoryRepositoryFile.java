package inventory.repository;


import inventory.model.*;
import inventory.validator.PartValidator;
import inventory.validator.ProductValidator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.StringTokenizer;

public class InventoryRepositoryFile {

    private static String filename = "data/items.txt";
    private InventoryRepositoryMemory inventoryRepositoryMemory;
    private PartValidator partValidator;
    private ProductValidator productValidator;

    public InventoryRepositoryFile(PartValidator partValidator, ProductValidator productValidator) {
        this.inventoryRepositoryMemory = new InventoryRepositoryMemory();
        this.partValidator=partValidator;
        this.productValidator=productValidator;
        readParts();
        readProducts();
    }

    public void readParts() {
        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());
        ObservableList<Part> listP = FXCollections.observableArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Part part = getPartFromString(line);
                if (part != null)
                    listP.add(part);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventoryRepositoryMemory.setAllParts(listP);
    }

    private Part getPartFromString(String line) {
        Part item = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("I")) {
            int id = Integer.parseInt(st.nextToken());
            inventoryRepositoryMemory.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            int idMachine = Integer.parseInt(st.nextToken());
            item = new InhousePart(id, name, price, inStock, minStock, maxStock, idMachine);
        }
        if (type.equals("O")) {
            int id = Integer.parseInt(st.nextToken());
            inventoryRepositoryMemory.setAutoPartId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String company = st.nextToken();
            item = new OutsourcedPart(id, name, price, inStock, minStock, maxStock, company);
        }
        return item;
    }

    public void readProducts() {
        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        ObservableList<Product> listP = FXCollections.observableArrayList();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                Product product = getProductFromString(line);
                if (product != null)
                    listP.add(product);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        inventoryRepositoryMemory.setProducts(listP);
    }

    private Product getProductFromString(String line) {
        Product product = null;
        if (line == null || line.equals("")) return null;
        StringTokenizer st = new StringTokenizer(line, ",");
        String type = st.nextToken();
        if (type.equals("P")) {
            int id = Integer.parseInt(st.nextToken());
            inventoryRepositoryMemory.setAutoProductId(id);
            String name = st.nextToken();
            double price = Double.parseDouble(st.nextToken());
            int inStock = Integer.parseInt(st.nextToken());
            int minStock = Integer.parseInt(st.nextToken());
            int maxStock = Integer.parseInt(st.nextToken());
            String partIDs = st.nextToken();

            StringTokenizer ids = new StringTokenizer(partIDs, ":");
            ObservableList<Part> list = FXCollections.observableArrayList();
            while (ids.hasMoreTokens()) {
                String idP = ids.nextToken();
                Part part = inventoryRepositoryMemory.lookupPart(idP);
                if (part != null)
                    list.add(part);
            }
            product = new Product(id, name, price, inStock, minStock, maxStock, list);
            product.setAssociatedParts(list);
        }
        return product;
    }

    public void writeAll() {

        ClassLoader classLoader = InventoryRepositoryFile.class.getClassLoader();
        File file = new File(classLoader.getResource(filename).getFile());

        BufferedWriter bw = null;
        ObservableList<Part> parts = inventoryRepositoryMemory.getAllParts();
        ObservableList<Product> products = inventoryRepositoryMemory.getProducts();

        try {
            bw = new BufferedWriter(new FileWriter(file));
            for (Part p : parts) {
                System.out.println(p.toString());
                bw.write(p.toString());
                bw.newLine();
            }

            for (Product pr : products) {
                StringBuilder line = new StringBuilder(pr.toString() + ",");
                ObservableList<Part> list = pr.getAssociatedParts();
                int index = 0;
                while (index < list.size() - 1) {
                    line.append(list.get(index).getPartId() + ":");
                    index++;
                }
                if (index == list.size() - 1)
                    line.append(list.get(index).getPartId());
                bw.write(line.toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Part addPart(Part part) {
        partValidator.validate(part);
        writeAll();
        return inventoryRepositoryMemory.addPart(part);
    }

    public void addProduct(Product product) {
        productValidator.validate(product);
        inventoryRepositoryMemory.addProduct(product);
        writeAll();
    }

    public void addProduct2(Product product) {
        inventoryRepositoryMemory.addProduct(product);
    }

    public int getAutoPartId() {
        return inventoryRepositoryMemory.getAutoPartId();
    }

    public int getAutoProductId() {
        return inventoryRepositoryMemory.getAutoProductId();
    }

    public ObservableList<Part> getAllParts() {
        return inventoryRepositoryMemory.getAllParts();
    }

    public ObservableList<Product> getAllProducts() {
        return inventoryRepositoryMemory.getProducts();
    }

    public Part lookupPart(String search) {
        return inventoryRepositoryMemory.lookupPart(search);
    }

    public Product lookupProduct(String search) {
        return inventoryRepositoryMemory.lookupProduct(search);
    }

    public void updatePart(int partIndex, Part part) {
        partValidator.validate(part);
        inventoryRepositoryMemory.updatePart(partIndex, part);
        writeAll();
    }

    public void updateProduct(int productIndex, Product product) {
        productValidator.validate(product);
        inventoryRepositoryMemory.updateProduct(productIndex, product);
        writeAll();
    }

    public void deletePart(Part part) {
        inventoryRepositoryMemory.deletePart(part);
        writeAll();
    }

    public void deleteProduct(Product product) {
        inventoryRepositoryMemory.removeProduct(product);
        writeAll();
    }

    public InventoryRepositoryMemory getInventory() {
        return inventoryRepositoryMemory;
    }

    public void setInventory(InventoryRepositoryMemory inventoryRepositoryMemory) {
        this.inventoryRepositoryMemory = inventoryRepositoryMemory;
    }
}