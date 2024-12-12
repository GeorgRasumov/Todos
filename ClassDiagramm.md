```mermaid
---
config:
  layout: elk
  theme: base
---

classDiagram
direction RL

    class TodoItem {
        + Int id
        + Int Date
        + Int Position
        + String title
        + String description
        + Boolean isCompleted
        + Boolean waiting
    }

    class IReadTodoItem {
        + getId()
        + getDate()
        + getPosition()
        + getTitle()
        + getDescription()
        + getIsCompleted()
        + getIsWaiting()
    }

    class RepetitiveTodo{
        - TodoItemIds
        - daysCreated
        + id
        + position
        + title
        + startDay
        + type
        + interval
    }

    class IReadRepetitiveTodo {
        + getId()
        + getDaysCreated()
        + getId()
        + getPosition()
        + getTitle()
        + getStartDate()
        + getType()
        + getIntervall()
    }

    class RepetitiveTodoRepository{

    }

    class RepetitiveTodoHandler{

    }

    class TodoHandler{

    }

    class TodoRepository{
    }

    class MainActivity{

    }

    class MainViewModel{

    }

    class TodoItemListView{

    }

    class TodoItemListViewModel{

    }

    class TodoItemView{

    }

    class TodoItemViewModel{

    }

    class RepetitiveTodoListView{

    }

    class RepetitiveTodoListViewModel{

    }

    class RepetitiveTodoView{

    }

    class RepetitiveTodoViewModel{

    }

    class CalenderView{

    }

    class CalenderViewModel{

    }

    %% Relationships
CalenderView --> CalenderViewModel : observes & calls
CalenderView --> TodoItemListView : creates & destroys
MainActivity --> MainViewModel : observes & calls
MainActivity --> CalenderView : creates & destroys
MainActivity --> TodoItemListView : creates & destroys
MainActivity --> RepetitiveTodoListView : creates & destroys
RepetitiveTodo ..|> IReadRepetitiveTodo : implements
RepetitiveTodoHandler ..> RepetitiveTodoRepository : observes & calls
RepetitiveTodoHandler ..> TodoRepository : observes & calls
RepetitiveTodoList ..|> IRepetitiveTodoReadList : inmplements
RepetitiveTodoListView --> RepetitiveTodoListViewModel : observes & calls
RepetitiveTodoListView --> RepetitiveTodoView : creates
RepetitiveTodoListViewModel --> RepetitiveTodoRepository : observes & calls
RepetitiveTodoListViewModel --> IRepetitiveTodoReadList : observes
RepetitiveTodoList .. RepetitiveTodo : has
RepetitiveTodoRepository --> RepetitiveTodoList : updates
RepetitiveTodoRepository --> RepetitiveTodo : updates
RepetitiveTodoView --> RepetitiveTodoViewModel : observes & calls
RepetitiveTodoViewModel --> RepetitiveTodoListViewModel : observes & calls
RepetitiveTodoViewModel --> IReadRepetitiveTodo : observes
TodoHandler ..> TodoRepository : observes & calls
TodoItem ..|> IReadTodoItem : implements
TodoItemView --> TodoItemViewModel : observes & calls
TodoItemViewModel --> IReadTodoItem : observes
TodoItemViewModel --> BaseListViewModel : observes & calls
TodoItemList .. IReadTodoItem : has
TodoItemList ..|> IReadTodoItemList : implements
TodoItemListView --> TodoItemListViewModel : observes & calls
TodoItemListView --> TodoItemView : creates
TodoItemListViewModel --> TodoRepository : observes & calls
TodoItemListViewModel --> IReadTodoItemList : observes

TodoRepository --> TodoItem : updates
TodoRepository --> TodoItemList : updates


