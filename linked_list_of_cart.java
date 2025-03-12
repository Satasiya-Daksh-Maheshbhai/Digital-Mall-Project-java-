
public class linked_list_of_cart {

    class Node {

        Node next;
        UserCart userCart;

        public Node(UserCart userCart) {
            this.userCart = userCart;
            next = null;
        }
    }

    Node head = null;
    Node temp01 = head;

    void addProductToCart(UserCart userCart) {
        Node n = new Node(userCart);
        if (head == null) {
            head = n;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
    }

    void removeProductFromCart(int pid) {
        if (head == null) {
            System.out.println("Your cart is empty!");
        } else {
            int flag = 0;
            Node temp = head;
            if (head.userCart.getpID() == pid) {
                Node del = head;
                head = head.next;
                del.next = null;
            } else {
                while (temp != null) {
                    if (temp.next.userCart.getpID() == pid) {
                        flag = 1;
                        break;
                    } else {
                        temp = temp.next;
                    }
                }

                if (flag == 1) {
                    temp.next = temp.next.next;
                } else {
                    System.out.println("No prduct found in cart!");
                }
            }
        }
    }

    boolean displayCart() {

        if (head == null) {
            System.out.println("No product in cart!");
            return false;
        } else {
            Node temp = head;
            while (temp != null) {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Product Id      :- " + temp.userCart.getpID());
                System.out.println("Product Name    :-" + temp.userCart.getpName());
                System.out.println("Product Quantity:-" + temp.userCart.getpQuantity());
                System.out.println("Product Price   :-" + temp.userCart.getpPrice());
                temp = temp.next;
            }
            return true;

        }
    }
}

class UserCart {

    int pID;
    String pName;
    int pQuantity;
    double pPrice;

    public UserCart(int pID, String pName, int pQuantity, double pPrice) {
        this.pID = pID;
        this.pName = pName;
        this.pQuantity = pQuantity;
        this.pPrice = pPrice;
    }

    public int getpID() {
        return pID;
    }

    public String getpName() {
        return pName;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public double getpPrice() {
        return pPrice;
    }
}
