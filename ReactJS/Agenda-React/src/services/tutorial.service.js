import http from "../http-common";

class TutorialDataService {
  getAll() {
    return http.get("/findAllTutorials");
  }

  get(id) {
    return http.get(`/getTutorialByID/${id}`);
  }

  create(data) {
    return http.post("/createTutorial", data);
  }

  update(data) {
    return http.put(`/updateTutorial`, data);
  }

  delete(id) {
    return http.delete(`/deleteTutorial/${id}`);
  }

  deleteAll() {
    return http.delete(`/deleteAllTutorials`);
  }

  findByTitle(titulo) {
    return http.get(`/findByTitleContaining/${titulo}`);
  }
}

export default new TutorialDataService();