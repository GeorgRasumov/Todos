```mermaid
---
config:
  layout: elk
  theme: base
---

classDiagram
direction RL

%% TodoItem
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

%% ITodoItemRepository
class ITodoItemRepository {
    <<interface>>
    + String title
    + String description
    + Boolean isCompleted
    + getID()
    + delete()
}

%% RepetitiveTodo
class RepetitiveTodo {
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

%% IRepetitiveTodoRepository
class IRepetitiveTodoRepository {
    <<interface>>
    + title
    + startDay
    + type
    + interval
    + getID()
    + delete()
}

%% RepetitiveTodoHandler
class RepetitiveTodoHandler {
    + getRepetitiveTodos()
    + createRepetitiveTodo()
    + createTodoItems(Day)
}
RepetitiveTodoHandler ..|> IRepetitiveTodoHandlerRepository : "implements"
RepetitiveTodoHandler --> RepetitiveTodo : "manages"
RepetitiveTodoHandler --> ITodoHandlerRepository : "calls"

%% IRepetitiveTodoHandlerRepository
class IRepetitiveTodoHandlerRepository {
    <<interface>>
    + createTodo()
    + getTodos() : IRepetitiveTodo
}

%% TodoHandler
class TodoHandler {
    + getTodoIds(Date = None) : List<Int> TodoId
    + getTodo(TodoId)
    + createToDo(): TodoId
    + changeTodoPosition(Date, Id, newPosition)
}
TodoHandler ..|> ITodoHandlerRepository : "implements"
TodoHandler --> TodoItem : "manages"
TodoHandler --> IRepetitiveTodoHandlerRepository : "calls"

%% ITodoHandlerRepository
class ITodoHandlerRepository {
    <<interface>>
    + getTodoIds(Date = None) : List<Int> TodoId
    + getTodo(TodoId)
    + createToDo(): TodoId
}

%% LoadSaveUtils
class LoadSaveUtils {
    + getUnusedId() : Id
    + updateTodo(Todo)
    + deleteTodo(Id)
    + getTodos(day) : TodoItem

    + getUnusedRepId() : Id
    + updateRepTodo(RepetitiveTodo)
    + deleteRepTodo(Id)
    + getRepTodos() : List<RepetitiveTodo
}

%% CalenderTodoViewModelSharedRepositoryView
class TodoViewModelSharedRepository {
    - TodoViewModels
    - TodoListViewModel
    + Event : deletePressedEvent
    + Event : changeDatePressedEvent<Date>
    + Event : selectionStateChangedEvent<int>
    + Observable : currentMode
    + addTodoViewModel()
    + removeTodoViewModel()
}

%% MainActivity
class MainActivity {
    item
}
MainActivity --> MainViewModel : observes & calls
MainActivity --> BaseListView : creates
MainActivity --> CalenderView : creates
MainActivity --> RepetitiveTodoListView : creates

%% MainViewModel
class MainViewModel {
    item
}

%% BaseListView
class BaseListView {
    item
}
BaseListView --> BaseListViewModel : observes & calls
BaseListView --> TodoItemView : observes & calls

%% BaseListViewModel
class BaseListViewModel {
    item
}
BaseListViewModel --> ITodoHandlerRepository : observes & calls
BaseListViewModel --> TodoViewModelSharedRepository : observes

%% TodoItemView
class TodoItemView {
    item
}
TodoItemView --> TodoItemViewModel : observes & calls

%% TodoItemViewModel
class TodoItemViewModel {
    item
}
TodoItemViewModel --> ITodoItemRepository : observes & calls
TodoItemViewModel --> TodoViewModelSharedRepository : observes

%% RepetitiveTodoListView
class RepetitiveTodoListView {
    item
}
RepetitiveTodoListView --> RepetitiveTodoListViewModel : observes & calls

%% RepetitiveTodoListViewModel
class RepetitiveTodoListViewModel {
    item
}
RepetitiveTodoListViewModel --> IRepetitiveTodoHandlerRepository : observes & calls
RepetitiveTodoListViewModel --> RepetitiveTodoView : creates


%%RepetitieTodoView
class RepetitiveTodoView{
    item
}
RepetitiveTodoView --> RepetitiveTodoViewModel : observes & calls


class RepetitiveTodoViewModel{
    item
}
RepetitiveTodoViewModel --> IRepetitiveTodoRepository : observes & calls

%% CalenderView
class CalenderView {
    item
}
CalenderView --> CalenderViewModel : observes & calls
CalenderView --> BaseListView : handles

%% CalenderViewModel
class CalenderViewModel {
    item
}
CalenderViewModel --> ITodoHandlerRepository : observes
