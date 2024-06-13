package com.example.client_vadim;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button depatrmentsAll, IllsAll, IllsName, bigDepartments, IllsBenefit, IllsByDepartmentsId, DepartmentsByIllsId, big, richIlls;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        depatrmentsAll = (Button) findViewById(R.id.DepartmentsAll);
        IllsAll = (Button) findViewById(R.id.IllsAll);
        IllsName = (Button) findViewById(R.id.IllsName);
        bigDepartments = (Button) findViewById(R.id.bigDepartments);
        IllsBenefit = (Button) findViewById(R.id.IllsBenefit);
        IllsByDepartmentsId=(Button) findViewById(R.id.illsByDepartmentsId);
        DepartmentsByIllsId = (Button) findViewById(R.id.departmentsByIllsId);
        big = (Button) findViewById(R.id.big);
        richIlls = (Button) findViewById(R.id.richIlls);

        editText = (EditText) findViewById(R.id.id);

        depatrmentsAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/departments/all";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfHospitalDepartments[] listOfHospitalDepartments = mapper.readValue(serverResponse, ListOfHospitalDepartments[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfHospitalDepartments l : listOfHospitalDepartments) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Name: " + l.getName() + "\n");
                                        stringBuilder.append("Head: " + l.getHead() + "\n");
                                        stringBuilder.append("Count: " + l.getCount() + "\n");
                                        stringBuilder.append("Price: " + l.getPrice() + "\n");
                                    }
                                    showMessage("Info", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        IllsAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/ills/all";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfIlls[] listOfIlls = mapper.readValue(serverResponse, ListOfIlls[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for(ListOfIlls l : listOfIlls) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Num: " + l.getNum() + "\n");
                                        stringBuilder.append("Dep_Name: " + l.getDepartment_name() + "\n");
                                        stringBuilder.append("Day: " + l.getDay() + "\n");
                                        stringBuilder.append("Month: " + l.getMonth() + "\n");
                                        stringBuilder.append("Year: " + l.getYear() + "\n");
                                        stringBuilder.append("PatName: " + l.getPatienName() + "\n");
                                        stringBuilder.append("IBen: " + l.getIsBenefit() + "\n");
                                    }
                                    showMessage("getAllDepartments", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        IllsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL = "http://10.0.2.2:8080/api/ills/names";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request.Builder()
                                .url(URL)
                                .get()
                                .build();

                        OkHttpClient client = new OkHttpClient();
                        Call call = client.newCall(request);

                        Response response;
                        try {
                            response = call.execute();
                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            String[] ills = mapper.readValue(serverResponse, String[].class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (String s : ills) {
                                        stringBuilder.append("Name: " + s + "\n" + "\n");
                                    }
                                    showMessage("all", stringBuilder.toString());
                                }
                            });


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        });
        bigDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/departments/bigDepartments";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfHospitalDepartments[] listOfHospitalDepartments = mapper.readValue(serverResponse, ListOfHospitalDepartments[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfHospitalDepartments l : listOfHospitalDepartments) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Name: " + l.getName() + "\n");
                                        stringBuilder.append("Head: " + l.getHead() + "\n");
                                        stringBuilder.append("Count: " + l.getCount() + "\n");
                                        stringBuilder.append("Price: " + l.getPrice() + "\n");
                                    }
                                    showMessage("getAllDepartments", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        IllsBenefit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/ills/benefit";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfIlls[] listOfIlls = mapper.readValue(serverResponse, ListOfIlls[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfIlls l : listOfIlls) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Num: " + l.getNum() + "\n");
                                        stringBuilder.append("Dep_Name: " + l.getDepartment_name() + "\n");
                                        stringBuilder.append("Day: " + l.getDay() + "\n");
                                        stringBuilder.append("Month: " + l.getMonth() + "\n");
                                        stringBuilder.append("Year: " + l.getYear() + "\n");
                                        stringBuilder.append("PatName: " + l.getPatienName() + "\n");
                                        stringBuilder.append("IBen: " + l.getIsBenefit() + "\n");
                                    }
                                    showMessage("getAllDepartments", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        IllsByDepartmentsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();
                if (id.isEmpty() || !isValidId(id)) {
                    showMessage("Error", "Please enter a valid ID.");
                    return;
                }

                String URL = "http://10.0.2.2:8080/api/ills/illsByDepartmentsId/" + id;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfIlls[] listOfIlls = mapper.readValue(serverResponse, ListOfIlls[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfIlls l : listOfIlls) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Num: " + l.getNum() + "\n");
                                        stringBuilder.append("Dep_Name: " + l.getDepartment_name() + "\n");
                                        stringBuilder.append("Day: " + l.getDay() + "\n");
                                        stringBuilder.append("Month: " + l.getMonth() + "\n");
                                        stringBuilder.append("Year: " + l.getYear() + "\n");
                                        stringBuilder.append("PatName: " + l.getPatienName() + "\n");
                                        stringBuilder.append("IBen: " + l.getIsBenefit() + "\n");
                                    }
                                    showMessage("getIlls", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        DepartmentsByIllsId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editText.getText().toString().trim();
                if (id.isEmpty() || !isValidId(id)) {
                    showMessage("Error", "Please enter a valid ID.");
                    return;
                }

                String URL = "http://10.0.2.2:8080/api/departments/departmentsByIllsId/" + id;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfHospitalDepartments hospitalDepartment = mapper.readValue(serverResponse, ListOfHospitalDepartments.class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    stringBuilder.append("id: " + hospitalDepartment.getId() + "\n");
                                    stringBuilder.append("Name: " + hospitalDepartment.getName() + "\n");
                                    stringBuilder.append("Head: " + hospitalDepartment.getHead() + "\n");
                                    stringBuilder.append("Count: " + hospitalDepartment.getCount() + "\n");
                                    stringBuilder.append("Price: " + hospitalDepartment.getPrice() + "\n");

                                    showMessage("getIlls", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });

        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/departments/big";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfHospitalDepartments[] listOfHospitalDepartments = mapper.readValue(serverResponse, ListOfHospitalDepartments[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfHospitalDepartments l : listOfHospitalDepartments) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Name: " + l.getName() + "\n");
                                        stringBuilder.append("Head: " + l.getHead() + "\n");
                                        stringBuilder.append("Count: " + l.getCount() + "\n");
                                        stringBuilder.append("Price: " + l.getPrice() + "\n");
                                    }
                                    showMessage("getAllDepartments", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        richIlls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = "http://10.0.2.2:8080/api/ills/richIlls";

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Request request = new Request.Builder()
                                    .url(URL)
                                    .get()
                                    .build();

                            OkHttpClient client = new OkHttpClient();
                            Call call = client.newCall(request);

                            Response response = call.execute();
                            if (!response.isSuccessful()) {
                                throw new IOException("Unexpected code " + response);
                            }

                            String serverResponse = response.body().string();
                            ObjectMapper mapper = new ObjectMapper();
                            ListOfIlls[] listOfIlls = mapper.readValue(serverResponse, ListOfIlls[].class);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    for (ListOfIlls l : listOfIlls) {
                                        stringBuilder.append("id: " + l.getId() + "\n");
                                        stringBuilder.append("Num: " + l.getNum() + "\n");
                                        stringBuilder.append("Dep_Name: " + l.getDepartment_name() + "\n");
                                        stringBuilder.append("Day: " + l.getDay() + "\n");
                                        stringBuilder.append("Month: " + l.getMonth() + "\n");
                                        stringBuilder.append("Year: " + l.getYear() + "\n");
                                        stringBuilder.append("PatName: " + l.getPatienName() + "\n");
                                        stringBuilder.append("IBen: " + l.getIsBenefit() + "\n");
                                    }
                                    showMessage("getAllDepartments", stringBuilder.toString());
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace(); // Log the exception
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showMessage("Error", "Failed to fetch data. Please try again.");
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
    public void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
    private boolean isValidId(String id) {
        return id.matches("\\d+");
    }
}