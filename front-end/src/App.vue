<template>
  <div>
    <h4>test</h4>
  </div>

  <div>
    <input v-model="answer" v-on:keyup.enter=mounted(answer)>
    <button v-on:click=mounted(answer)>완료</button>
  </div>

  <div>
    <a> result : {{ chatGpt }}</a>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  methods: {
    async mounted(answer) {
      let result = await axios.get("http://localhost:8080/gpt/v1/chat/msg?message=" + answer);
      this.chatGpt = result.data;
    },
  },

  data() {
    return {
      answer: "",
      chatGpt: "",
    }
  },
  components: {
  }
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
