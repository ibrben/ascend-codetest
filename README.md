# Interview Quiz

This assignment is an application to retrieve information about COVID-19 reports from the public API of DDC OpenData.

---

The service provides 2 APIs to generate COVID-19 reports.

- The first one is an API to get COVID-19 cases from the public API, with the optional parameter `date` to filter the results by date.
```
path: /covid-cases/reports
method: GET
params: date (optional)
```

Example response:
```json
[
    {
        "txn_date": "2020-01-12",
        "new_case": 0,
        "total_case": 0,
        "province": "กระบี่",
        "new_case_excludeabroad": 0,
        "total_case_excludeabroad": 0
    },
    {
        "txn_date": "2020-01-12",
        "new_case": 1,
        "total_case": 1,
        "province": "กรุงเทพมหานคร",
        "new_case_excludeabroad": 0,
        "total_case_excludeabroad": 0
    },
   ...
]
```

- The second API is an api to find the highest and lowest COVID-19 cases.
```
path: /covid-cases/reports/highest-lowest
method: GET
params: none
```

Example response:
```json
{
    "highest": [
        {
            "province": "สมุทรสาคร",
            "totalCases": 17123
        }
    ],
    "lowest": [
        {
            "province": "บึงกาฬ",
            "totalCases": 0
        }
    ]
}
```

We will get COVID-19 data via this API:
https://covid19.ddc.moph.go.th/api/Cases/round-1to2-by-provinces

---

### Tasks

You have to do all the tasks to complete the assignment.

1. Create a web client to retrieve data from the public API.
2. Using the CovidReportApiService bean (default) to generate reports directly from the public API. 
3. Verify that the results of the two APIs are as expected.
   (After this step, the API may take a long time to process, so we are going to fetch the data to the database when starting the server and process it from the database.)
4. Switch to using the CovidReportDbService bean to create a report from the database.
5. Verify that the results of the two APIs are as expected.

Note:
- Fix some issues that may occur when implementing the API.
- All unit tests should be passed.
- Push the project to your personal git and set it to public (we will see how you work by the commits).

---

### Public API Documents

https://drive.google.com/file/d/1BDYrYHahSUaiLnKnRxDAnkPSaZfzKmg9/view
