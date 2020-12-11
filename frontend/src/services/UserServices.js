import http from 'axios';




export default {

  updateUser(user) {
    return http.put('/profile/' + user.id , user);
  },

  getUser() {
      return http.get('/profile');
  },

  getAllUsers(id) {
    return http.get('/profile/' + id);
  }



}