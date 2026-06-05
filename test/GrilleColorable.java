// import java.awt.*;
// import java.awt.event.*;
// import java.util.LinkedHashMap;
// import java.util.Map;
// import javax.swing.*;

// public class GrilleColorable extends JFrame {

//     // ── Palette de couleurs disponibles ──────────────────────────────────────
//     private static final Map<String, Color> PALETTE = new LinkedHashMap<>();
//     static {
//         PALETTE.put("Effacer",      Color.WHITE);
//         PALETTE.put("Rouge",        new Color(109, 7 ,  26));
//         PALETTE.put("Rouge Colore", Color.RED);
//         PALETTE.put("Orange",       new Color(235, 130, 30));
//         PALETTE.put("Jaune",        new Color(240, 200, 20));
//         PALETTE.put("Vert",         new Color(50,  180, 80));
//         PALETTE.put("Bleu",         new Color(40,  110, 220));
//         PALETTE.put("Violet",       new Color(140, 60,  200));
//         PALETTE.put("Mauve",        Color.MAGENTA);
//         PALETTE.put("Rose",         new Color(230, 100, 160));
//         PALETTE.put("Marron",       new Color(140, 80,  30));
//         PALETTE.put("Noir",         Color.BLACK);
//         PALETTE.put("Gris",         new Color(150, 150, 150));
//         PALETTE.put("Cyan",         new Color(30,  200, 220));
//         PALETTE.put("Saumon",       new Color(240, 150, 120));
//         PALETTE.put("Vert citron",  new Color(140, 210, 40));
//     }

//     private final int n;
//     private final JPanel[][] cases;
//     private Color couleurActuelle = new Color(109, 7 ,  26); // rouge par défaut
//     private JLabel labelCouleur;
//     private boolean modeDessin = false; // dessin en glissant la souris

//     public GrilleColorable(int n) {
//         this.n = n;
//         this.cases = new JPanel[n][n];

//         this.setTitle("Grille Colorable " + n + "×" + n);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setLayout(new BorderLayout(0, 0));
//         this.getContentPane().setBackground(new Color(28, 28, 35));

//         this.add(creerBandeauTitre(), BorderLayout.NORTH);
//         this.add(creerPanneauGrille(), BorderLayout.CENTER);
//         this.add(creerBarreOutils(), BorderLayout.SOUTH);

//         this.pack();
//         this.setMinimumSize(new Dimension(500, 560));
//         this.setLocationRelativeTo(null);
//         this.setVisible(true);
//     }

//     // ── Bandeau titre ────────────────────────────────────────────────────────
//     private JPanel creerBandeauTitre() {
//         JPanel bandeau = new JPanel(new BorderLayout());
//         bandeau.setBackground(new Color(20, 20, 28));
//         bandeau.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

//         JLabel titre = new JLabel("✦ GRILLE " + n + "×" + n);
//         titre.setFont(new Font("Monospaced", Font.BOLD, 18));
//         titre.setForeground(new Color(210, 210, 240));

//         bandeau.add(titre, BorderLayout.WEST);
//         bandeau.add(btnEffacer, BorderLayout.EAST);
//         return bandeau;
//     }

//     // ── Grille N×N ───────────────────────────────────────────────────────────
//     private JPanel creerPanneauGrille() {
//         JPanel wrapper = new JPanel(new GridBagLayout());
//         wrapper.setBackground(new Color(28, 28, 35));
//         wrapper.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));

//         JPanel grille = new JPanel(new GridLayout(n, n, 1, 1));
//         grille.setBackground(new Color(60, 60, 75)); // couleur des séparateurs

//         int tailleCase = Math.max(24, Math.min(64, 560 / n));

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 JPanel cellule = new JPanel();
//                 cellule.setBackground(Color.WHITE);
//                 cellule.setPreferredSize(new Dimension(tailleCase, tailleCase));
//                 cellule.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210), 0));
//                 cellule.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

//                 final int fi = i, fj = j;

//                 // Clic simple
//                 cellule.addMouseListener(new MouseAdapter() {
//                     public void mousePressed(MouseEvent e) {
//                         modeDessin = true;
//                         colorierCase(fi, fj);
//                     }
//                     public void mouseReleased(MouseEvent e) {
//                         modeDessin = false;
//                     }
//                     public void mouseEntered(MouseEvent e) {
//                         if (modeDessin) colorierCase(fi, fj);
//                         // Survol : légère teinte
//                         if (!cases[fi][fj].getBackground().equals(couleurActuelle)) {
//                             cases[fi][fj].setBorder(BorderFactory.createLineBorder(
//                                     couleurActuelle.darker(), 2));
//                         }
//                     }
//                     public void mouseExited(MouseEvent e) {
//                         cases[fi][fj].setBorder(BorderFactory.createLineBorder(
//                                 new Color(200, 200, 210), 0));
//                     }
//                 });

//                 cases[i][j] = cellule;
//                 grille.add(cellule);
//             }
//         }

//         wrapper.add(grille);
//         return wrapper;
//     }

//     private void colorierCase(int i, int j)
//     {
//         cases[i][j].setBackground(couleurActuelle);
//         cases[i][j].repaint();
//     }

//     private void toutEffacer()
//     {
//         for (int i = 0; i < n; i++)
//             for (int j = 0; j < n; j++)
//                 cases[i][j].setBackground(Color.WHITE);
//     }

//     // ── Barre d'outils (menu déroulant + aperçu couleur) ────────────────────
//     private JPanel creerBarreOutils() {
//         JPanel barre = new JPanel(new FlowLayout(FlowLayout.LEFT, 14, 10));
//         barre.setBackground(new Color(20, 20, 28));
//         barre.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(55, 55, 70)));

//         // Label
//         JLabel lblChoix = new JLabel("Couleur :");
//         lblChoix.setFont(new Font("SansSerif", Font.PLAIN, 13));
//         lblChoix.setForeground(new Color(170, 170, 200));

//         // Menu déroulant
//         String[] noms = PALETTE.keySet().toArray(new String[0]);
//         JComboBox<String> combo = new JComboBox<>(noms);
//         combo.setSelectedIndex(1); // Rouge par défaut
//         combo.setFont(new Font("SansSerif", Font.PLAIN, 13));
//         combo.setBackground(new Color(40, 40, 55));
//         combo.setForeground(new Color(220, 220, 240));
//         combo.setFocusable(false);
//         combo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//         combo.setPreferredSize(new Dimension(160, 30));

//         // Aperçu de la couleur choisie
//         labelCouleur = new JLabel();
//         labelCouleur.setOpaque(true);
//         labelCouleur.setBackground(couleurActuelle);
//         labelCouleur.setPreferredSize(new Dimension(28, 28));
//         labelCouleur.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 130), 2));

//         // Couleur personnalisée
//         JButton btnCustom = new JButton("🎨 Autre…");
//         btnCustom.setFont(new Font("SansSerif", Font.PLAIN, 12));
//         btnCustom.setBackground(new Color(60, 60, 75));
//         btnCustom.setForeground(new Color(200, 200, 220));
//         btnCustom.setBorder(BorderFactory.createCompoundBorder(
//                 BorderFactory.createLineBorder(new Color(90, 90, 110), 1),
//                 BorderFactory.createEmptyBorder(4, 10, 4, 10)));
//         btnCustom.setFocusPainted(false);
//         btnCustom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//         btnCustom.addMouseListener(new MouseAdapter() {
//             public void mouseEntered(MouseEvent e) { btnCustom.setBackground(new Color(80, 80, 100)); }
//             public void mouseExited(MouseEvent e)  { btnCustom.setBackground(new Color(60, 60, 75)); }
//         });
//         btnCustom.addActionListener(e -> {
//             Color c = JColorChooser.showDialog(this, "Choisir une couleur", couleurActuelle);
//             if (c != null) {
//                 couleurActuelle = c;
//                 labelCouleur.setBackground(c);
//                 combo.setSelectedIndex(-1); // désélectionne la liste
//             }
//         });

//         // Listener combo
//         combo.addActionListener(e -> {
//             String choix = (String) combo.getSelectedItem();
//             if (choix != null) {
//                 couleurActuelle = PALETTE.get(choix);
//                 labelCouleur.setBackground(couleurActuelle);
//             }
//         });

//         barre.add(lblChoix);
//         barre.add(combo);
//         barre.add(labelCouleur);
//         barre.add(Box.createHorizontalStrut(6));
//         barre.add(btnCustom);
//         return barre;
//     }

//     // ── Point d'entrée ───────────────────────────────────────────────────────
//     public static void main(String[] args) {
//         int taille = 10; // valeur par défalabelCouleurut

//         if (args.length > 0) {
//             try {
//                 taille = Integer.parseInt(args[0]);
//                 if (taille < 2 || taille > 50) {
//                     System.err.println("⚠ Taille hors limites (2–50). Valeur par défaut : 10.");
//                     taille = 10;
//                 }
//             } catch (NumberFormatException ex) {
//                 System.err.println("⚠ Paramètre invalide. Valeur par défaut : 10.");
//             }
//         }

//         final int n = taille;
//         SwingUtilities.invokeLater(() -> {
//             // Apparence système
//             try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
//             catch (Exception ignored) {}
//             new GrilleColorable(n);
//         });
//     }
// }