

TP4

1. Ajouter le mapping associant une AvantPremière (Preview) à un Film (OneToOne), en unidirectionnel
2. Reprenez les films de votre base  et associez leur une instance de preview, commitez la transaction. Une erreur devrait se produire. En effet, les éléments doivent être ajoutés au contexte de persistence avant de les associer (essayez en appelant entityManager.persist sur chacun des Previews créés)
3. Vérifier l’association des éléments dans la base de donnée
4. Requêtez sur l’ensemble des films et affichez les informations des films ainsi que leur avant première