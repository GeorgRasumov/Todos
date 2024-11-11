# Ziele
## Primäre Ziele

- entwicklung noch einer weiteren Todo App
- soll gewisses grundmaß an Funktionalität bieten jedoch nicht überladen sein
- es soll sehr schnell und einfahc möglich sein neue Todos hinzuzufügen ohne sich direkt gedanken machen zu müssen, wann dieses Todo erledigt werden soll
- es wird vom Nutzer eigeninitiative gefordert, um bearbeitung der Todos zu organisieren
- App soll jedoch eine einfache Möglichkeit bieten diese Organisation vorzunehmen

## Optionale Ziele
- möglichkeit des Ein- und Ausschaltens von Funktionen und Ansichten
- Anordung verschiedener Ansichten einstellbar

# Technische Anforderungen
- Verwendung von Kotlin zur Programmierung
- Primär entwicklung für Android
- jedoch soll portierbarkeit zu anderen Systemen betrachtet werden
- bei jedem künftigen System sollte natives UI verwendet werden
- verwendung von MVVM-Architektur
- Model und ViewModel sollen keine Android-Spezifischen Pakete importieren
- somit müssen bei Portierung zu anderen Systemen lediglich Views angepasst werden
- funktionen welche Android spezifisch sind, jedoch nicht mit dem View Zusammenhängen sollen per Dependency Injection ans Model übergeben werden


# Funktionale Anforderungen

- zwischen folgenden views kann durch wischen nach links und rechts navigiert werden:
  - Kalender
  - warten
  - heute
  - morgen
  - später
- bei jedem öffnen der App wird "heute" angezeigt
- durch weiter wischen kann vom letzten View zum ersten gesprungen werden und umgekehrt
- es soll von jedem View aus möglich sein ein Context-Menü zu öffnen
  - durch button oben rechts oder eventuell durch wischen nach unten
- über Context-Menü kann zu jedem View navigiert werden. Neben dem oben genannten Views können folgende weitere Views geöffnet werden:
  - Widerkehrende Todos
  - vergangene Todos
  - Optionen
 
## Base-List-View
- Todos werden in verschiedenen Arten von Listen angezeigt, welche jedoch prinzipiell den gleichen hier beschriebenen Aufbau haben
- Liste kann sich in zwei verschiedenen Modi befinden: Abarbeiten und Bearbeiten
- Abarbeiten:
  - dient Primär zum Begutachten und Abhacken der Todos
  - alle Todos sind untereinander angeordnet
  - einzelnes Todo besteht aus Titel und Button um Beschreibung zu öffnen
  - durch klicken auf Todo wird dieses abgehackt oder Abhacken wird rückgängig gemacht
  - rechts von jedem Todo befindet sich Button um Beschreibung des Todos zu öffnen
  - Beschreibung ist Textfeld unter dem Todo, welches bearbeitet werden kann
  - öffnen mehrerer Beschreibungen gleichzeitig möglich
  - unter der Liste befindet sich Leiste mit zwei Buttons
  - ein Button dient dazu in Bearbeitungsmodi zu wechseln
  - anderer Button dient dazu neues Todo unten der Liste hinzuzufügen
- Bearbeiten:
  - wird durch unteren button oder durch langes Klicken auf Todo aktiv
  - durch anklicken von Todo wird dieses ausgewählt oder auswahl wird wiederrufen
  - Auswahl belibig vieler Todos möglich
  - rechts von jedem Todo befindet sich Button um den Titel zu ändern
  - link von jedem Todo befindet sich Button, welcher zum Verschieben dient
  - zwischen zwei Todos befindet sich rechts von der Grenze ein button um neues Todo zwischen ihnen einzufügen
  - unterhalb der Todo-Liste befindet sich Optionsmenü mit folgenden Optionen:
     - alle ausgewählten Todos Löschen
     - alle ausgewählten Todos in andere Liste verschieben
     - wechsel in Modi "Abarbeiten"
  

## Todos-Heute
- Liste aller Todos, welche der Nutzer für den heutigen Tag geplant hat
- vorhandene Todos haben folgende Funktionen
 - änderung der Position in der Auflistung
 - ändern der Bezeichnung
 - hinzufügen und ändern einer Beschreibung
 - abhacken und abhacken rückgängig machen
- folgende Funktionen sollen für mehrer Todos gleichzeitig ausgeführt werden können:
  - verschieben auf anderen Tag
  - löschen
- Hizufügen von Todos an beliebiger Stelle in der Liste
- erledigte Todos werden automatisch am Tagesende nach "erledigt" verschoben


## Todos-morgen
- gleicher Aufbau wie Todos-Heute
- bei Tageswechsel werden Todos von hier automatisch auf heute verschoben
- reihenfolge von Todos Heute soll gleich bleiben wie von Todos-Morgen

## Todos-später
- gleicher Aufbau wie Todos-Heute
- einzelne Todos können ohne Zeitangabe angelegt werden
- Möglichkeit der Gruppierung von Todos

## Warten 
- gleicher Aufbau wie Todos-Heute

## Kalendar
- Dient zum Hinzufügen von Todos für spezifischen Tag
- Todo wird automatisch zu "Todo-Morgen" hinzugefügt
- Todos Heute und Todos Morgen werden im Kalendar dargestellt
- keine Überschneidung zwischen Kalender und Todos-Später
- Hinzufügen von widerkehrenden Todos im Kalendar möglich

## vergangene Todos
- gleicher Aufbau wie Todos-Heute
- enthält alle Todos, welche erledigt, jedoch nicht gelöscht wurden
- todos werden zum Tageswechsel automatisch hierhin verschoben

## wiederkehrende Todos



