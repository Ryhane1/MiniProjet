//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


//acheterProduit
        public static void acheterProduit (Scanner input){
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
        }
    }

}
