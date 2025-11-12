import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static ArrayList<String> produits = new ArrayList<>(List.of("Eau", "Soda", "Chips", "Chocolat"));
    static ArrayList<Double> prix = new ArrayList<>(List.of(5.0, 8.0, 12.0, 15.0));
    static ArrayList<Integer> stock = new ArrayList<>(List.of(10, 5, 7, 3));

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("=== Distributeur Automatique ===");
                System.out.println(
                        "1. Afficher les produits\n" +
                        "2. Acheter un produit\n" +
                        "3. Afficher le stock\n" +
                        "4. Recharger le stock\n" +
                        "5. Quitter");

                System.out.print("\nEntrez votre choix : ");
                choice = input.nextInt();
                System.out.println("Choix : " + choice);

                switch (choice) {
                    case 1:
                        afficherProduits(produits, prix, stock);
                        break;

                    case 2:
                        acheterProduit(input);
                        break;

                    case 3:
                        afficherStock(produits, stock);
                        break;

                    case 4:
                        rechargeStock(input, produits, stock);
                        break;

                    case 5:
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println("Choix invalide.");
                }

            } catch (Exception e) {
                System.out.println("Une erreur inattendue est survenue : " + e.getMessage());
            }

            System.out.println(); 
        } while (choice != 5);

        input.close();
    }

    // Afficher produits  
    public static void afficherProduits(ArrayList<String> produits, ArrayList<Double> prix, ArrayList<Integer> stock) {
        System.out.println("\nProduits disponibles : ");
        for (int i = 0; i < produits.size(); i++) {
            System.out.println((i + 1) + ". " + produits.get(i) + " (" + prix.get(i) + " MAD) - Stock : " + stock.get(i));
        }
    }

    // Acheter un produit
    public static void acheterProduit(Scanner input) {
        try {
            afficherProduits(produits, prix, stock);
            System.out.print("Entrez le numéro du produit : ");
            int choice2 = input.nextInt();

            if (choice2 < 1 || choice2 > produits.size()) {
                System.out.println("Produit invalide !");
                return;
            }

            int index = choice2 - 1;

            if (stock.get(index) <= 0) {
                System.out.println("Désolé, ce produit est en rupture de stock !");
                return;
            }

            System.out.print("Entrez le montant inséré : ");
            double montant = input.nextDouble();
            double prixProduit = prix.get(index);

            if (montant < prixProduit) {
                System.out.println("Montant insuffisant !");
            } else {
                double monnaie = montant - prixProduit;
                System.out.println("Vous avez acheté : " + produits.get(index));
                System.out.println("Monnaie rendue : " + monnaie + " MAD");

                stock.set(index, stock.get(index) - 1);
            }

        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de l'achat : " + e.getMessage());
        }
    }

    // Afficher le stock des produits
    public static void afficherStock(ArrayList<String> produits, ArrayList<Integer> stock) {
        System.out.println("\nStock actuel des produits : ");
        for (int i = 0; i < produits.size(); i++) {
            System.out.println(produits.get(i) + " : " + stock.get(i) + " unités");
        }
    }

    // Recharger le stock d’un produit (Admin)
    public static void rechargeStock(Scanner input, ArrayList<String> produits, ArrayList<Integer> stock) {
        try {
            afficherStock(produits, stock);

            System.out.print("Numéro du produit recharger : ");
            int choix = input.nextInt();

            if (choix < 1 || choix > produits.size()) {
                System.out.println("Numero invalide");
                return;
            }

            int index = choix - 1;

            System.out.print("quantite à ajouter : ");
            int quantite = input.nextInt();

            if (quantite <= 0) {
                System.out.println("quantite invalide");
                return;
            }

            stock.set(index, stock.get(index) + quantite);
            System.out.println("Stock mis a jour pour " + produits.get(index) + " : " + stock.get(index) + " unités");

        }   catch (Exception e) {
            System.out.println("Une erreur est survenue lors du rechargement : " + e.getMessage());
        }
    }
}
