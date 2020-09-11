| Вопрос        | Ответ           | Question in english  | Answer in english  |
| ---- |-------------| ----|-------------|
| Что за тип такой — Unit? Зачем нужен?| Тоже самое что void в Java https://habr.com/ru/post/425077/ | | The type with only one value: the Unit object. This type corresponds to the void type in Java. |
|Как компилируются object declarations| public final class Test {||
||public static final Test INSTANCE;||
   ||static {||
      ||Test var0 = new Test();||
      ||INSTANCE = var0;||
   ||}||
||}||
