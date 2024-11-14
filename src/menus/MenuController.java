package menus;

import java.util.Stack;

/**
 * The type Menu controller.
 */
public class MenuController {
    private final Stack<Menu> menuStack;
    private String message;
    private boolean running = true;
    private String encryptionMethod;
    private String hashAlgorithm;

    /**
     * Instantiates a new Menu controller.
     */
    public MenuController() {
        this.menuStack = new Stack<>();
        this.menuStack.push(new MainMenu(this));
    }

    /**
     * Sets current menu.
     *
     * @param menu the menu
     */
    public void setCurrentMenu(Menu menu) {
        menuStack.push(menu);
    }

    /**
     * Go back.
     */
    public void goBack() {
        if (menuStack.size() > 1) {
            menuStack.pop();
            System.out.println("Retour au menu précédent.");
        } else {
            System.out.println("Vous êtes déjà au menu principal.");
        }
    }

    /**
     * Stop.
     */
    public void stop() {
        running = false;
    }

    /**
     * Start.
     */
    public void start() {
        while (running) {
            System.out.flush();
            Menu currentMenu = menuStack.peek();
            currentMenu.display();
            currentMenu.handleInput();
        }
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets encryption method.
     *
     * @return the encryption method
     */
    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    /**
     * Sets encryption method.
     *
     * @param encryptionMethod the encryption method
     */
    public void setEncryptionMethod(String encryptionMethod) {
        this.encryptionMethod = encryptionMethod;
    }

    /**
     * Gets hash algorithm.
     *
     * @return the hash algorithm
     */
    public String getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Sets hash algorithm.
     *
     * @param hashAlgorithm the hash algorithm
     */
    public void setHashAlgorithm(String hashAlgorithm) {
        this.hashAlgorithm = hashAlgorithm;
    }

    /**
     * Clear hash algorithm.
     */
    public void clearHashAlgorithm() {
        this.hashAlgorithm = null;
    }
}