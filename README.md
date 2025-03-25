1. CURL để tạo user key:
  curl --location --request POST 'http://localhost:8080/api/keys?username=Tran'

2. CURL để lấy user key:
   curl --location 'http://localhost:8080/api/keys?pageSize=20&pageNumber=1&username=Hoang'

3. CURL để xóa user key:
   curl --location --request DELETE 'http://localhost:8080/api/keys/1'
