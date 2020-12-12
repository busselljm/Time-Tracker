import http from 'axios';

export default {
  updateProfile(user) {
    console.log('user put', user)
    return http.put(`/users/profile/${user.id}` , user);
  },
  getUser(user) {
    return http.get('/users/profile/', user);
  },
  getAllUsers() {
    return http.get(`/users`);
  }
}