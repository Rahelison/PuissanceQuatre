import java.util.Scanner;
public class puissance4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);

		int Colonne;
		int Ligne ;
		int JetonsaAligner;
		
		// ON DEMANDE AU JOUEUR D'ENTRER LA TAILLE DU PLATEAU DE JEU

		Terminal.ecrireString("Quelle est la taille du plateau de jeu ? "); 
																			
		Colonne = Terminal.lireInt();
		Terminal.sautDeLigne();

		Terminal.ecrireString("Combien de jetons à aligner pour gagner la partie ? ");
		
		// ON DEMANDE AU JOUEUR D'ENTRER  LE NOMBRE DE JETONS A ALIGNER AFIN DE GAGNER LA PARTIE
		
		JetonsaAligner = Terminal.lireInt();
		Terminal.sautDeLigne();
		
		/** CREATION DU PLATEAU DE JEU**/

		Ligne = Colonne ; // LE NOMBRE DE LIGNE EST EGALE AU NOMBRE DE COLONNE
		char[][] PlateauDeJeu = new char[Colonne][Ligne];
		// CRÃ‰ATION DU PLATEAU DE JEU
		for (int x = 0; x < Colonne; x++)
			for (int y = 0; y < Ligne; y++)
				PlateauDeJeu[x][y] = '.'; // LES CASES SONT INITIALEMENT VIDES ET REPRESENTEES PAR DES POINTS ' . '

		int gagnant = 0;

		
		for (int i = 1; i <= Colonne * Ligne; i++) {

			// AFFICHE L'ETAT DU PLATEAU DE JEU A CHAQUE TOUR FINI

			Terminal.ecrireStringln("ETAT   DU   PLATEAU  DE  JEU:   ");

			for (int loop = 0; loop < Colonne + 2 + 2 * Colonne; loop++)

				Terminal.ecrireChar( '-' );
			Terminal.sautDeLigne();

			for (int y = 0; y < Ligne; y++) {

				Terminal.ecrireChar( '|' );

				for (int x = 0; x < Colonne; x++) {
					System.out.print(" " + PlateauDeJeu[x][y] + " ");

				}

				Terminal.ecrireChar( '|' );
				Terminal.sautDeLigne();

			}

			for (int loop = 0; loop < Colonne + 2 + 2 * Colonne; loop++)
				System.out.print('-');
			Terminal.sautDeLigne();
			Terminal.sautDeLigne();

			// PLACEMENT DES JETONS

			System.out.println("    Tour du joueur " + (i % 2 == 1 ? '1' : '2'));
			Terminal.sautDeLigne();
			System.out.println("    Veuillez entrer le numÃ©ro de la colonne: "); // ON DEMANDE A CHAQUE JOUEUR D'ENTRER LE NUMERO DE LA COLONNE OU IL VEUT PLACER SON JETON
			boolean placement = false;
			int colonne = -1;
			while (!placement) {
				colonne = -1;
				String ligne = scanner.nextLine();

				// VERIFICATION DU NUMERO ENTRE PAR CHAQUE JOUEUR
				try {
					colonne = Integer.valueOf(ligne);

					if (colonne >= 1 && colonne <= Colonne) {
						if (PlateauDeJeu[colonne - 1][0] != '.') {
							Terminal.ecrireStringln("Colonne pleine");
						} else {
							placement = true;
						}
					} else {
						Terminal.ecrireStringln(" Le numero de le colonne est incorrect ");
					}

				} catch (Exception e) {
					Terminal.ecrireStringln(" Le numero de le colonne est incorrect ");
				}

			}

			int rang = Ligne - 1;

			while (PlateauDeJeu[colonne - 1][rang] != '.') {
				rang--;
			}
			PlateauDeJeu[colonne - 1][rang] = (i % 2 == 1 ? 'X' : 'O');

			// ON DETECTE LE JETON DE CHAQUE JOUEUR ET LE NUMERO DU JOUEUR

			char symbole = (i % 2 == 1 ? 'X' : 'O');

			// NOMBRE MAXIMAL DE JETONS ALIGNES
			int max = 0;
			int x;
			int y;
			int somme;

			// DETECTION DE LA PARTIE FINIE A LA DIAGONALE

			x = colonne - 1;
			y = rang;
			somme = -1;

			while (y >= 0 && x >= 0 && PlateauDeJeu[x][y] == symbole) {
				y--;
				x--;
				somme++;
			}
			x = colonne - 1;
			y = rang;
			while (y < Ligne && x < Colonne && PlateauDeJeu[x][y] == symbole) {
				y++;
				x++;
				somme++;
			}
			if (somme > max)
				max = somme;

			// DETECTION DE LA PARTIE FINIE A LA DIAGONALE
			x = colonne - 1;
			y = rang;
			somme = -1;
			while (y >= 0 && x < Colonne && PlateauDeJeu[x][y] == symbole) {
				y--;
				x++;
				somme++;
			}
			x = colonne - 1;
			y = rang;
			while (y < Ligne && x >= 0 && PlateauDeJeu[x][y] == symbole) {
				y++;
				x--;
				somme++;
			}
			if (somme > max)
				max = somme;

			// DETECTION DE LA PARTIE FINIE A LA VERTICALE
			x = colonne - 1;
			y = rang;
			somme = -1;
			while (y >= 0 && PlateauDeJeu[x][y] == symbole) {
				y--;
				somme++;
			}
			y = rang;
			while (y < Ligne && PlateauDeJeu[x][y] == symbole) {
				y++;
				somme++;
			}
			if (somme > max)
				max = somme;
			
			// DETECTION DE LA PARTIE FINIE A L'HORIZONTALE
			x = colonne - 1;
			y = rang;
			somme = -1;
			while (x >= 0 && PlateauDeJeu[x][y] == symbole) {
				x--;
				somme++;
			}
			x = colonne - 1;
			while (x < Colonne && PlateauDeJeu[x][y] == symbole) {
				x++;
				somme++;
			}
			if (somme > max)
				max = somme;

			if (max >= JetonsaAligner) {
				gagnant = (i % 2 == 1 ? 1 : 2);
				i = Colonne * Ligne + 1;

			}

			Terminal.sautDeLigne();
		}

		/** AFFICHAGE DU  PLATEAU DE JEU FINAL **/

		Terminal.sautDeLigne();
		Terminal.ecrireStringln    ("                               ******************************                                ");
		Terminal.ecrireStringln    ("                                    FIN  DE LA  PARTIE                                       ");
		Terminal.ecrireStringln    ("                               ******************************                                ");

		if (gagnant == 0)
			Terminal.ecrireStringln("                                      PARTIE NULLE                                           "); // SI LE PLATEAU DE JEU EST REMPLI
		if (gagnant == 1)
			Terminal.ecrireStringln("                           LE   JOUEUR 1   A   GAGNE  LA   PARTIE                          "); // SI LE JOUEUR 1 A GAGNE LA PARTIE
		if (gagnant == 2)
			Terminal.ecrireStringln("                           LE   JOUEUR 2   A   GAGNE  LA   PARTIE                          "); // SI LE JOUEUR 2 A GAGNE LA PARTIE

		Terminal.sautDeLigne();

		for (int loop = 0; loop < Colonne + 2 + 2 * Colonne; loop++)
			Terminal.ecrireChar('-');
		System.out.println();

		for (int y = 0; y < Ligne; y++) {
			Terminal.ecrireChar('|');
			for (int x = 0; x < Colonne; x++) {
				System.out.print(" " + PlateauDeJeu[x][y] + " ");
			}
			Terminal.ecrireChar('|');
			Terminal.sautDeLigne();
		}

		for (int loop = 0; loop < Colonne + 2 + 2 * Colonne; loop++)

			Terminal.ecrireChar('-');
		Terminal.sautDeLigne();

	}

}
