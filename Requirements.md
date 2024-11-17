# 1. Ziele

## 1.1. Primäre Ziele

- Entwicklung einer weiteren Todo-App.
- Die App soll ein gewisses Grundmaß an Funktionalität bieten, jedoch nicht überladen sein.
- Grundgedanke ist, dass auf einfache Art immer eine Tages-Todo Liste erstellt wird, welche abgearbeitet werden kann.
- Dabei soll nicht zu viel definiert werden, wie zum Beispiel die genaue Uhrzeit oder Dauer einzelner Todos.
- Hinzufügen von Todos für spezifischen Tag, aber auch für unspezifischen Tag soll schnell möglich sein.
- insbesonder soll es schnell gehen ein Todo für morgigen Tag anzulegen.
- App sol intuitive Mechanik von Wiederkehrendes Todos bieten.
- App soll wo es intuitiv Sinn macht automatisiert arbeiten, jedoch wird auch eigeninitative von Nutzer verlangt.
  - Beispielsweise werden Todos zum Tageswechsel automatisch von Morgen auf Heute verschoben.
  - Todos ohne definiertes Datum verbleiben aber in "Später"-Liste, solanger der Nutzer nicht eingreift.
- Die App soll jedoch eine einfache Möglichkeit zur Organisation der Todos bieten.

## 1.2. Optionale Ziele

- Möglichkeit des Ein- und Ausschaltens von Funktionen und Ansichten.
- Anpassbare Anordnung verschiedener Ansichten.

# 2. Technische Anforderungen

- Verwendung von Kotlin zur Programmierung.
- Primär Entwicklung für Android.
- Portierbarkeit zu anderen Systemen soll berücksichtigt werden.
- Bei jedem zukünftigen System soll natives UI verwendet werden.
- Verwendung einer MVVM-Architektur.
- Model und ViewModel sollen keine gerätespezifischen Pakete importieren.
- Bei der Portierung zu anderen Systemen müssen lediglich die Views angepasst werden.
- Android-spezifische Funktionen, die nicht mit dem View zusammenhängen, sollen per Dependency Injection ans Model übergeben werden.

# 3. Funktionale Anforderungen

## 3.1. Navigation

- Navigation durch Wischen nach links und rechts zwischen folgenden Views:
  - Kalender ↔ Warten ↔ Heute ↔ Morgen ↔ Später
  - Kalender und Später liegen auch nebeneinander.
- Über eine Sidebar kann zu folgenden Views navigiert werden:
  - Alle oben beschriebenen Views.
  - Wiederkehrende Todos erstellen.
  - Optionen.

## 3.2. Base-List-View

- Todos werden in verschiedenen Arten von Listen angezeigt, die denselben Aufbau haben.
- Bei Todo-Listen, die sich auf einen bestimmten Tag beziehen, bleibt die Reihenfolge bei Tageswechsel gleich.
- Die Liste kann sich in drei Modi befinden: Abarbeiten, Bearbeiten und Hinzufügen.

### 3.2.1. Hinzufügen-Button

- Sichtbar im Modus "Abarbeiten" und "Bearbeiten".
- Befindet sich mittig unten und liegt über der Liste.
- Beim Scrollen nach unten verschiebt sich die Liste nach oben, sodass das letzte Item nicht verdeckt wird.
- Kurzes Drücken fügt ein Todo am Ende der Liste ein.
- Langes Drücken öffnet den Hinzufügen-Modus.

### 3.2.2. Hinzufügen-Modus

- Wird durch den Button aktiviert.
- Rückkehr in den Abarbeiten-Modus durch Drücken der Zurück-Taste oder durch Klicken auf ein Todo.
- Rechts mittig zwischen zwei Todos befinden sich Buttons, um ein neues Todo einzufügen.
- Links von jedem Todo befindet sich ein Button, um dieses nach oben oder unten zu verschieben.

### 3.2.3. Abarbeiten-Modus

- Primär zum Begutachten und Abhaken der Todos.
- Todos sind untereinander angeordnet.
- Jedes Todo besteht aus einem Titel und einem Button, um die Beschreibung zu öffnen.
- Klicken auf ein Todo hakt dieses ab oder macht das Abhaken rückgängig.
- Rechts von jedem Todo befindet sich ein Button, um die Beschreibung zu öffnen.
- Mehrere Beschreibungen können gleichzeitig geöffnet werden.
- Durch langes Drücken und Halten kann ein Todo in der Liste verschoben werden.

### 3.2.4. Bearbeiten-Modus

- Aktivierung durch langes Klicken und Loslassen eines Todos.
- Rückkehr in den Abarbeiten-Modus, wenn kein Todo mehr ausgewählt ist oder die Zurück-Taste gedrückt wird.
- Todos können durch kurzes Klicken ausgewählt oder die Auswahl aufgehoben werden.
- Beliebig viele Todos können gleichzeitig ausgewählt werden.
- Rechts von zuletzt ausgewählten Todos erscheint eine Leiste mit folgenden Optionen:
  - Titel ändern (nur wenn ein Todo ausgewählt ist).
  - Verschieben auf eine andere Liste.
  - Tag ändern (ähnliche Funktionalität wie Verschieben).
  - Löschen.

## 3.3. Todos Heute

- Liste aller Todos, die der Nutzer für den heutigen Tag geplant hat.
- Erledigte Todos werden am Tagesende automatisch nach "Erledigt" verschoben.
- Neue Todos werden zum Tageswechsel automatisch auf "Heute" geschoben.
- Am Tag nicht erledigte Todos verbleiben in der Liste "Heute".

## 3.4. Todos Morgen

- Darstellung aller Todos, die für den morgigen Tag datiert sind.
- Enthält auch wiederkehrende Todos.
- Todos mit Terminangabe (siehe Kalender) werden zum Tageswechsel automatisch eingefügt.

## 3.5. Todos Später

- Enthält Todos ohne definierte Zeit zur Erledigung.

## 3.6. Warten

- Funktionalität wie "Todos Später".
- Ablageort für Todos, bei denen man auf etwas wartet (z. B. eine Antwort auf eine E-Mail).

## 3.7. Kalender

- Dient zum Hinzufügen von Todos für spezifische Tage.
- Startbild ist die Monatsansicht des Kalenders.
- Punkte zeigen an, dass ein Tag Todos enthält.
  - Unterschiedliche Farben für einmalige und wiederkehrende Todos.
  - Graue Punkte für erledigte Todos.
- Anklicken eines Tages öffnet eine Base-List-View für diesen Tag.
- Rückkehr zur Kalenderansicht durch die Zurück-Taste.
- Wischen nach links und rechts wechselt zwischen Tagen.

## 3.8. Wiederkehrende Todos

### 3.8.1. User-Interface

- Ansicht besteht aus einer Liste, in der die Todos untereinander mit Titeln angezeigt werden.
- Über ein Einstellungsmenü können verschiedene Arten von wiederkehrenden Todos ein- und ausgeblendet werden:
  - Tages-, Wochen-, Monats-, Jahres- und benutzerdefinierte Intervalle.
- Modi: Bearbeiten und Hinzufügen (standardmäßig Bearbeiten).

#### Einstellungs-Popup

- Öffnet sich beim Erstellen oder Bearbeiten eines Todos.
- Folgende Funktionen sind verfügbar:
  - Titel ändern.
  - Startdatum festlegen.
  - Anzahl und Einheit des Wiederholabstands festlegen.
    - Anzahl: Ganze Zahl.
    - Einheit: Tage, Wochen, Monate, Jahre.

#### Hinzufügen-Button

- Sichtbar im Bearbeiten-Modus.
- Befindet sich mittig unten und liegt über der Liste.
- Beim Scrollen nach unten verschiebt sich die Liste nach oben, sodass das letzte Item nicht verdeckt wird.
- Kurzes Drücken fügt ein Todo am Ende der Liste ein.
- Langes Drücken öffnet den Hinzufügen-Modus.

#### Hinzufügen-Modus

- Aktivierung durch den Button.
- Rückkehr in den Bearbeiten-Modus durch die Zurück-Taste oder durch Klicken auf ein Todo.
- Rechts mittig zwischen zwei Todos befinden sich Buttons, um ein neues Todo einzufügen.
- Links von jedem Todo befindet sich ein Button, um dieses nach oben oder unten zu verschieben.

#### Bearbeiten-Modus

- Kurzes Klicken auf ein Todo öffnet das Einstellungs-Popup.
- Rechts von jedem Todo befindet sich ein Button zum Löschen.
- Durch langes Drücken und Halten kann die Position verändert werden.

### 3.8.2. Mechanik

- Wiederkehrende Todos erstellen automatisch in festgelegten Abständen neue Todos.
- Die Erstellung erfolgt bis zu dem Datum, das der Nutzer im Kalender einsehen kann.
- Aus wiederkehrenden Todos generierte Todos haben dieselbe Funktionalität wie normale Todos:
  - Sie können umbenannt und verschoben werden.
- Werden wiederkehrende Todos gelöscht, werden alle daraus erstellten offenen Todos gelöscht (auch bearbeitete).
  - Abgehakte Todos bleiben erhalten.
  - Gleiches gilt, wenn Startdatum oder Wiederholabstand geändert wird.
