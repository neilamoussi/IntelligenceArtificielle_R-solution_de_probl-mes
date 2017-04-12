# IntelligenceArtificielle_Résolution_des_problémes

- Cette application est developpée en JAVA
- Cette application peut fonctionner avec n'importe quelle base de connaissances en entrée.

# A propos

Ce Projet vise à résoudre le problème de cruches d’eau avec le choix des
algorithmes suivants :

- l'algorithme de recherche en largeur.

- l'algorithme A* avec deux heuristique différentes.

Il s’agit d’implémenter d’abord la base de connaissance en choisissant
les structures de données adéquates, l’algorithme d’unification et la
méthode qui permet de déclencher tous les règles déclenchables d’un
État courant.

- calculer heuristique
Pour calculer l’heuristique d’un état on a utilisée l’api de scripting java en utilisant
l’objet ScriptEngine plus précisent la méthode eval() qui prend en compte un
argument qui est une chaîne de caractères. Si cette chaîne représente une
expression, eval() évaluera l'expression cette méthode nous permis de faires des
opération arithmétique comme valeur absolue, somme ,produit des chaines de
caractères et retourne le résultat en chaine de caractères

# l'algorithme A* 

Pour implémenter l’algorithme A* on a besoin d’abord d’une liste ouverte qui
comprend tous les nœuds développés à partir d’un état choisit .Et une liste
fermée qui contient chaque nœud choisit à chaque niveau

- Le choix d’un nœud à examiner à partir de la liste ouvertes se fait après
un calcul=heuristique+le niveau dans lequel on se trouve.

- Qui a la valeur la plus petite sera choisit, retiré de la liste ouvertes et ajouté à
la liste fermé .Chaque nœud a aussi un père qui l’a fait déclencher


# Recherche en largeur

Le coût total estimé f est associé à chaque arc car l’heuristique est égal à 0
G(n)=F(n).

# Conclusion

Après l’implémentation des différentes algorithmes et lorsque nous avons fait
une comparaison entre les deux algorithmes A* et recherche en largeur on a
vérifié que l’algorithme le plus fiable et qui donne un résultat avec un minimum
de cout et de temps c’est l’algorithme A*.






