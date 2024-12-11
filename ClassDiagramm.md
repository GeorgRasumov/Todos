```mermaid
---
config:
  layout: elk
  theme: default
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
        + delete()
    }
    TodoItem ..|> ITodoItemRepository : "implements"
    TodoItem --> LoadSaveUtils : "uses"

    class ITodoItemRepository {
        <<interface>>
        + String title
        + String description
        + Boolean isCompleted
        + getID()
        + delete()
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
        + createTodoIfNeeded(date) : TodoItem
        + delete()
    }
    RepetitiveTodo ..|> IRepetitiveTodoRepository : "implements"
    RepetitiveTodo --> LoadSaveUtils : "uses"

    class IRepetitiveTodoRepository{
        <<interface>>
        + title
        + startDay
        + type
        + interval
        + getID()
        + delete()
    }

    class RepetitiveTodoHandler{

        + getRepetitiveTodos()
        + createRepetitiveTodo()
        + createTodoItems(Day)

    }
    RepetitiveTodoHandler ..|> IRepetitiveTodoHandlerRepository : "implements"
    RepetitiveTodoHandler --> RepetitiveTodo : "manages"

    class IRepetitiveTodoHandlerRepository{
        <<interface>>
        + createTodo()
        + getTodos() : IRepetitiveTodo

    }

    class TodoHandler{

        + getTodoIds(Date = None) : List<Int> TodoId
        + getTodo(TodoId)
        + createToDo(): TodoId
        + changeTodoPosition(Date, Id, newPosition)

    }
    TodoHandler ..|> ITodoHandlerRepository : "implements"
    TodoHandler --> TodoItem : "manages"

    class ITodoHandlerRepository{
        <<interface>>
        + getTodoIds(Date = None) : List<Int> TodoId
        + getTodo(TodoId)
        + createToDo(): TodoId
    }

    class LoadSaveUtils{

        + getUnusedId() : Id
        + updateTodo(Todo)
        + deleteTodo(Id)
        + getTodos(day) : TodoItem

        + getUnusedRepId() : Id
        + updateRepTodo(RepetitiveTodo)
        + deleteRepTodo(Id)
        + getRepTodos() : List<RepetitiveTodo>

    }


    class MainActivity{

    }
    MainActivity --> MainViewModel : observes & calls

    class MainViewModel{

    }

    class BaseListView{

    }
    BaseListView --> BaseListViewModel : observes & calls

    class BaseListViewModel{

    }
    BaseListViewModel --> ITodoHandlerRepository : observes & calls

    class TodoItemView{

    }
    TodoItemView --> TodoItemViewModel : observes & calls

    class TodoItemViewModel{

    }
    TodoItemView --> ITodoItemRepository : observes & calls


    class RepetitiveTodoListView{

    }
    RepetitiveTodoListView --> RepetitiveTodoListViewModel : observes & calls

    class RepetitiveTodoListViewModel{

    }
    RepetitiveTodoListViewModel --> IRepetitiveTodoHandlerRepository : observes & calls

    class CalenderView{

    }
    CalenderView --> CalenderViewModel : observes & calls

    class CalenderViewModel{

    }
    CalenderViewModel --> ITodoHandlerRepository : observes
