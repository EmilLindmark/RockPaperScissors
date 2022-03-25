# RockPaperScissors

## Guide:
OBS: Denna guide antar att applikationen körs på samma maskin som båda spelarna använder. Om så inte är fallet, byt ut "http://localhost:8080" mot adressen där applikationen körs.

Ladda ned postman via https://www.postman.com/downloads/ eller använd postmans webbgränssnitt https://www.postman.com/.

### Starta ett spel:
I postman välj att skapa ett nytt POST-anrop och ange adressen http://localhost:8080/api/games.
I request-body'n ange namn på spelaren som startar spelet. Ex:
{
  "name": "Nils"
}

Anropet ger tillbaka ett UUID som behövs för att en till spelare ska kunna ansluta och för att spelarna ska kunna göra sina drag. Spara UUID't.

### Anslut till ett spel
För att ansluta till ett spel behövs UUID't från första steget. Skapa sedan ett POST-anrop och ange adressen: http://localhost:8080/api/games/{id}/join där {id} är UUID't från första steget.
I request-body'n ange namn på spelaren som vill ansluta till spelet. Ex:
{
  "name": "Olle"
}

### Gör ett drag
För att göra ett drag behövs UUID't från första steget. Skapa sedan ett POST-anrop och ange adressen: http://localhost:8080/api/games/{id}/move där {id} är UUID't från första steget.
I request-body'n ange namn på spelaren som vill göra draget och vilket drag spelaren vill göra. Giltiga drag är Rock, Paper och Scissors. Ex:
{
  "name": "Olle"
  "move": "Rock"
}

### Hämsta spelets nuvarande tillstånd
Efter att spelet är startat kan man hela tiden hämta spelets nuvarande tillstånd. Detta görs genom att skapa ett GET-anrop och ange adressen: http://localhost:8080/api/games/{id} där {id} är UUID't från första steget.

