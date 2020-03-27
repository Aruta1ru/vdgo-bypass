<template>

 <v-container
 fluid
 fill-height >

        <v-layout
          align-center
          justify-center >

          <v-flex xs12 sm8 md4>
            <v-card  class="elevation-10">
              <v-toolbar light flat >
              <img src="https://oblgaznnov.ru/images/logo.png" height=50/>
              </v-toolbar>

              <v-card-text>
                <v-form
                 id="app" action="/login"  method="post">

                  <v-text-field
                    id="username"
                    name="username"
                    :prepend-icon="account"
                    placeholder="Имя пользователя"
                    type="text">
                  </v-text-field>

                  <v-text-field
                    id="password"
                    placeholder="Пароль"
                    name="password"
                    autocomplete="on"
                    :prepend-icon= "lock"
                    type="password" >
                    <v-text-field
                    type="hidden"
                    name="csrf [${csrf.token}]">
                    </v-text-field>
                    </v-text-field>

                <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" type="submit" > Войти </v-btn>
              </v-card-actions>
                </v-form>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>

</template>

<script>
import {mdiLock, mdiAccount} from '@mdi/js'

export default {
        data () {
             return {
           lock: mdiLock ,
           account: mdiAccount
               }
               }
               }
</script>