const schedule = require('node-schedule');
const axios = require('axios');

const j = schedule.scheduleJob('1 * * * * *', function() {
  axios
    .get("http://localhost:8080/Naver_Reservation_final/autoReservationStatus.do")
    .then((response) => {
      console.log("성공");
    })
    .catch((error) => {
      console.log(error);
    });
});