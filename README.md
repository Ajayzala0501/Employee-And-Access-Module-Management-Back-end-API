
# Employee And Access Module Management System Back-End APIs



## API Reference

#### Add new employee details to the system.

```http
  POST /apis/employee/addNewEmployeeDetails
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `employee` | `Object` | **Required** The employee details to be added, provided in the request body as JSON. |

#### Retrieve a list of all employee details from the system.

```http
  GET /apis/employee/getEmployees
```
#### Retrieve employee details based on a specific employee ID.

```http
  GET /apis/employee/getEmployeeById/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required** The unique identifier of the employee to retrieve. |

#### Update employee details based on a specific employee ID.

```http
  PUT /apis/employee/updateEmployeeDetails/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required** The unique identifier of the employee to retrieve. |
| `employee` | `Object` | **Required** The employee details to be added, provided in the request body as JSON.|

#### Delete an employee's details based on a specific employee ID.

```http
  DELETE /apis/employee/deleteEmployeeDetails/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `long` | **Required** The unique identifier of the employee to retrieve. |


#### Delete all employee records in the system.

```http
  DELETE /apis/employee/deleteAllEmployeeDetails
```

#### Retrieve details of all modules in the system.

```http
  GET /apis/modules/getAllModules
```

#### Allow module access to a specific employee by their IDs.

```http
  POST /apis/modules/addModuleAccessToEmployee/{empId}/module/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `empId` | `long` | **Required** The unique identifier of the employee to grant module access. |
| `moduleId` | `long` | **Required** The unique identifier of the module to be granted access.|

#### Retrieve all modules associated with a specific employee based on their ID.

```http
  GET /apis/modules/getAllModulesByEmployeeId/{empId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `empId` | `long` | **Required** The unique identifier of the employee for whom modules are to be retrieved.|

#### Retrieve module details based on a specific module ID.

```http
  GET /apis/modules/getModuleById/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `moduleId` | `long` | **Required** The unique identifier of the module to retrieve.|

#### Retrieve all employees associated with a specific module based on its ID.

```http
  GET /apis/modules/getAllEmployeesByModuleId/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `moduleId` | `long` | **Required** The unique identifier of the module for which employees are to be retrieved.|

#### Update module details based on a specific module ID.

```http
  PUT /apis/modules/updateModuleDetails/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `moduleId` | `long` | **Required** The unique identifier of the module to update.|
| `modules` | `Object` | **Required** The updated module details provided in the request body as JSON.|

#### Remove access to a specific module from an employee based on their IDs.

```http
  DELETE /apis/modules/removeModuleAccessFromEmployee/{empId}/Module/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `empId` | `long` | **Required** The unique identifier of the employee from whom module access is to be removed.|
| `moduleId` | `long` | **Required** The unique identifier of the module for which access is to be removed.|

#### Delete module details based on a specific module ID.

```http
  DELETE /apis/modules/deleteModuleById/{moduleId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `moduleId` | `long` | **Required** The unique identifier of the module to delete.|
