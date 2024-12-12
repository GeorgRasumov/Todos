```mermaid
---
config:
  layout: elk
  theme: base
---

classDiagram
direction RL

    class TodoItem {
        + mutableDateType
        + observableDateType
        + mutablePosition
        + observablePosition
        + mutableTitle
        + observableTitle
        + mutableDescription
        + observableDescription
        + mutableIsCompleted
        + observableIsCompleted
        + getId()
    }

    class IReadTodoItem {
        + observablePosition
        + observableTitle
        + observableDescription
        + observableIsCompleted
        + getId()
    }

    class TodoRepository{
        + addGetTodosCallback(Callable)
        + removeGetTodosCallback(Callable)
        + getTodoList(dateType)
        + getTodo(id)
        + addTodo(title, dateType, position)
        + editTitle(id, newTitle)
        + editDateType(id, newDateType)
        + editPosition(id, newPosition)
        + editDescription(id, newDescription)
        + editIsCompleted(id, isCompleted)
        + deleteTodo(id)
    }

    class TodoItemList{
        + TodoDeletedEvent
        + TodoCreatedEvent
        + getReadableTodos()
        + addTodo(RepetitiveTodo)
        + removeTodo(id)
    }

    class IReadTodoItemList{
        + TodoDeletedEvent
        + TodoCreatedEvent
        + getReadableTodos()
    }

    class RepetitiveTodo{
        - TodoItemIds
        - daysCreated
        + mutableTitle
        + observableTitle
        + mutableType
        + observableType
        + mutablePosition
        + observablePosition
        + mutableStartDate
        + observableStartDate
        + mutableIntervall
        + observableIntervall
        + getId()
    }

    class IReadRepetitiveTodo {
        + observableTitle
        + observableType
        + observablePosition
        + observableStartDate
        + observableIntervall
        getId()
    }

    class RepetitiveTodoRepository{
        - RepetitiveTodoList
        + getReadableTodoList()
        + getTodo(id)
        + addTodo(title, repetitionType, position, startDay, intervall)
        + editTitle(id, newTitle)
        + editRepetitionType(id, newRepetitionType)
        + editPosition(id, newPosition)
        + editStartDay(id, newStartDay)
        + editIntervall(id, newIntervall)
        + deleteTodo(id)
    }

    class RepetitiveTodoList{
        + TodoDeletedEvent
        + TodoCreatedEvent
        + getReadableTodos()
        + addTodo(RepetitiveTodo)
        + removeTodo(id)
    }

    class IReadRepetitiveTodoList{
        + TodoDeletedEvent
        + TodoCreatedEvent
        + getReadableTodos()
    }

    class RepetitiveTodoHandler{

    }

    class TodoHandler{

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
RepetitiveTodoList ..|> IReadRepetitiveTodoList : inmplements
RepetitiveTodoListView --> RepetitiveTodoListViewModel : observes & calls
RepetitiveTodoListView --> RepetitiveTodoView : creates
RepetitiveTodoListViewModel --> RepetitiveTodoRepository : observes & calls
RepetitiveTodoListViewModel --> IReadRepetitiveTodoList : observes
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


