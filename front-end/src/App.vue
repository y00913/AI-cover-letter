<template>
  <div>
    <h4>test</h4>
  </div>

  <div>
    <input v-model="question" v-on:keyup.enter=mounted(question)>
    <button v-on:click=mounted(question)>완료</button>
  </div>

  <br />

  <div>
    <a> result : {{ chatGpt }}</a>
  </div>
</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  methods: {
    async mounted(question) {
      let result = await axios.get("http://218.237.234.37:8000/gpt/v1/chat/msg?question=" + question);
      this.chatGpt = result.data;
    },
  },

  data() {
    return {
      question: "",
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
