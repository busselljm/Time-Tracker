import http from 'axios';



export default {

  updateUser(user) {
    return http.put('/profile', user);
  },

  getUser() {
      return http.get('/profile');
  }

}