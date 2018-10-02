GOMES Antoine
AMBROSINO Gwenaël
FORT Tz'ayik

Ce projet a été réalisé sous l'IDE IntelliJ !
Pour toutes questions vous pouvez m'envoyer un mail à : antoine.gomes@etu.univ-lyon1.fr
 -----------------------------
| FAIRE FONCTIONNER LE PROJET |
 -----------------------------

Ouvrir le projet sur votre IDE préféré.
Vérifier que toutes les dépendances ont été bien importé.
Pour exécuter le code, faite run le main.

/!\Attention si vous aviez une application Jade déjà lancé,
il se peut que la console vous rapporte une erreur de port.
Afin de libérer le port, vous pouvez kill le processus associé à ce port.
Pour résoudre ce problème tapez dans un terminal :
fuser 1099/tcp
kill [ID Process]
Relancer ensuite le main.

 ------------------------
| COMMENT CREER UN OU DES AGENTS |
 ------------------------

pour utiliser le comportement normal: il fonctionne comme dans l’exemple
BookBuyerAgent -> arg : livre
BookSellerAgent -> sans arg

pour utiliser le comportement aléatoire:
Créer un BookSellerAgent, lui donner un nom, puis le créer. Donner ensuite un nom et un prix au livre dans la fenêtre qui vient de s’ouvrir.
Créer un BookBuyerAgent, lui donner un nom et ne pas lui donner d’argument

pour utiliser le comportement négociateur:
Créer un BookSellerNegAgent, lui donner un nom, puis le créer. Donner ensuite un nom et un prix au livre dans la fenêtre qui vient de s’ouvrir.
Créer un BookBuyerNegAgent, lui donner un nom puis le donner en argument le titre du livre et le prix maximum auquel on veut l’acheter avec cette syntaxe “titre,prix” (sans mettre les guillemets)

pour utiliser le comportement achat à tout prix:
Créer un BookSellerAgent, lui donner un nom, puis le créer. Donner ensuite un nom et un prix au livre dans la fenêtre qui vient de s’ouvrir.
Créer un BookBuyerAgent, lui donner un nom puis le donner en argument le titre du livre et le prix maximum auquel on veut l’acheter avec cette syntaxe “titre,prix” (sans mettre les guillemets)



