<template>
<v-container>
    <v-form id="app" action="/login"  method="post" >

                   <v-col cols="12" md="4"  >
                   <v-text-field outlined id="username" name="username" label="Имя пользователя">
                   </v-text-field>
                   </v-col>

                   <v-col cols="12" md="4">
                   <v-text-field outlined id="password" type="password" name="password" label="Пароль">
                   <v-text-field type="hidden" name="csrf [${csrf.token}]"></v-text-field>
                   </v-text-field>
                   </v-col>

                   <v-col  cols="12" md="4">
                   <v-btn type="submit" >Вход</v-btn>
                   </v-col>
        </v-form>
       </v-container>
</template>