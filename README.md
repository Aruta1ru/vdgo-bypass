# vdgo-bypass
Аpplication for GPGR-NN hard workers

1. Установить npm и NODE.js, проверить что установилось (nmp -v)    
2. Скачать Yarn с офф сайта   
2(альт.) Установить Chocolatey, проверить что установилось (choco -v)
   Можно поставить вместе с NODE.js, при подготовке к установке нажать на галочку.
   С помощью Chocolatey поставить Yarn 
    (choco install yarn)
3. Инициализируем Yarn 
    (yarn init)
5. Добавить библиотеки 
    (yarn add vue vue-resource)
6. Добавить девелоперские зависимости 
    (yarn add -D webpack webpack-cli webpack-dev-server babel-loader @babel/core @babel/preset-env vue-loader vue-template-compiler css-loader vue-style-loader)
    
7. Запуск приложения в 2 этапа:
    - фронт (npm start)
    - бэк (обычный запуск приложения java)
