package entities;

import jakarta.persistence.*;

@Entity
    @Table(name = "etudiants")  // nom de table recommandé au pluriel
    public class etudiant {  // nom de classe en PascalCase (majuscule au début)

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_etudiant")
        private Long idEtudiant;

        @Column(name = "nom_et", nullable = false, length = 50)
        private String nomEt;

        @Column(name = "prenom_et", nullable = false, length = 50)
        private String prenomEt;

        @Column(nullable = false, unique = true)
        private Long cin;

        @Column(length = 100)
        private String ecole;



}
