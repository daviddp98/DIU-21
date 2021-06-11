import http from "../http-common";

class ProductoDataService {
  getAll() {
    return http.get("/products");
  }

  getProductById(id) {
    return http.get(`/${id}`);
  }

  create(data) {
    return http.post("/products", data);
  }

  update(data) {
    return http.put(`/products/update/`, data);
  }

  delete(id) {
    return http.delete(`/products/${id}`);
  }
}

export default new ProductoDataService();