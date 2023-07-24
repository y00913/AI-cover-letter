<template>
  <body>
    <div id="head">
      <h2>AI 자기소개서</h2>
    </div>

    <form v-on:submit.prevent="ask" id="ask">
      <div>
        <div>
          <textarea v-model="question" placeholder="질문 ex) 성격을 서술하시오." id="question"></textarea>
        </div>

        <div>
          <textarea v-model="information" placeholder="정보 ex) 차분함. 솔직함. 리더십이 있음." id="information"></textarea>
        </div>
      </div>

      <div id="button">
        <div v-if="!isLoading">
          <button v-on:keyup.enter="submit">완료</button>
        </div>

        <div v-if="isLoading">
          <a style="color:#8b8a8a;">40초 정도 소요됩니다.</a>
        </div>
      </div>

    </form>

    <div id="result-box">

      <div id="loading" v-if="isLoading">
        <img src="./assets/loading.gif">
      </div>

      <div id="result" v-if="!isLoading">
        <a v-if="chatGpt == ''" style="color:#8b8a8a;"> 
          <br><br>
          <p>자기소개서를 'ChatGPT' 가 작성해드립니다.</p>
          <P>질문 칸과 정보 칸을 입력하고 완료를 눌러주세요.</P>
          <p>결과가 이 곳에 출력됩니다. </p>
        </a>
        <a> {{ chatGpt }} </a>
      </div>
      <br>
    </div>

    <div>
      <p style="font-weight: bold; margin-top: 60px">
        made by <a v-bind:href="`https://velog.io/@y00913`" target="_blank" style="text-decoration: none;">y00913</a>
      </p>
    </div>
  </body>
</template>

<script>
import axios from "axios"
export default {
  name: 'App',

  methods: {
    async ask() {
      this.isLoading = true;
      const result = await axios.post("https://aicl.kro.kr:8000/gpt/v1/chat/msg", {
        question: this.question,
        information: this.information,
      });
      this.chatGpt = result.data;
      this.isLoading = false;
    },
  },

  data() {
    return {
      question: "",
      information: "",
      chatGpt: "",
      isLoading: false,
    }
  },
  components: {
  }
}

</script>

<style>
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  text-align: center;
  min-height: 90vh;
  margin: auto;
}

#head {
  text-decoration: none;
  font-size: 1.2em;
  margin-bottom: 10px;
}

#result-box {
  border-radius: 15px;
  padding: 10px;
  border: 2px solid #b3b0b0;
  margin: 30px;
  width: 45vw;
  height: auto;
  min-height: 300px;
  align-items: center;
  white-space: pre-wrap;
  word-wrap: break-word;
}

#question {
  width: 300px;
  height: 50px;
}

#information {
  width: 300px;
  height: 50px;
}

#button {
  margin-top: 15px;
}

#ask {
  width: 300px;
  height: 180px;
}

#loading {
  margin-top: 100px;
}

#result {
  margin: 20px;
}
</style>
