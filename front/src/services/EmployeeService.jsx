import axios from 'axios'

const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:8080/api/employee';

class EmployeeService {

    getAllEmployees() {
        return axios.get(EMPLOYEE_BASE_REST_API_URL, {headers: {'Authorization' : 'Bearer ' + localStorage.getItem("token"), 'Content-Type': 'application/json'}})
    }

    exportXls() {
        return axios.get('http://localhost:8080/api/employee/export/xls', {responseType: 'blob', headers: {'Content-Type': 'application/json'}})
    }

    exportDocx() {
        return axios.get('http://localhost:8080/api/employee/export/docx', {responseType: 'blob', headers: {'Content-Type': 'application/json'}})
    }

    authenticate(employee){
        return axios.post('http://localhost:8080/login', employee, {headers: {'Content-Type': 'application/json'}});
    }

    createEmployee(employee) {
        return axios.post(EMPLOYEE_BASE_REST_API_URL, employee)
    }

    signup(employee){
        return axios.post('http://localhost:8080/register', employee, {headers: {'Content-Type': 'application/json'}});
    }

    // signup(employee){
    //     return axios.post('http://localhost:8080/signup', employee, {headers: {'Content-Type': 'application/json'}});
    // }

    getEmployeeById(employeeId) {
        return axios.get(EMPLOYEE_BASE_REST_API_URL + '/' + employeeId);
    }

    updateEmployee(employeeId, employee) {
        return axios.put(EMPLOYEE_BASE_REST_API_URL + '/' + employeeId, employee);
    }

    deleteEmployee(employeeId) {
        return axios.delete(EMPLOYEE_BASE_REST_API_URL + '/' + employeeId, {headers: {'Content-Type': 'application/json'}});
    }
}

export default new EmployeeService();