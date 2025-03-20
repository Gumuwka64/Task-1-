First, Check UserServiceImpl class
when u made,an object of UserService class firstoval make a flag method setWay true or false
when flag true ,class will be using methods of Hibernate
when flag false , class will be using methods of JDBC
for ur comfort in main class and test class was made boolean constant "HIBER"

В первую очередь проверьте UserServiceImpl class
когда вы создаете экземпляр класса UserServiceImpl  ,в первую очередь нужно установить флаг в методе
setWay true или false
Когда передаем true в метод ,класс UserServiceImpl использует методы Hibernate
Когда передаем false ,UserServiceImpl использует методы JDBC
для вашего комфорта были добавлены константы main class и test class "HIBER"