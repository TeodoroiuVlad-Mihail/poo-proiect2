package main;

final class Singleton {
    // Static variable reference of single_instance
    // of type Singleton
    private static Singleton singleInstance = null;

    private Client client;

    // Constructor
    private Singleton() {
    }

    // Static method to create instance of Singleton class
    public static Singleton getInstance() {
        if (singleInstance == null) {
            singleInstance = new Singleton();
        }

        return singleInstance;
    }

    /**
     * getter for client
     */
    public Client getClient() {
        return client;
    }

    /**
     * setter for client
     */
    public void setClient(final Client client) {
        this.client = client;
    }
}
