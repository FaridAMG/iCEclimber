// #include <json-c/json.h>
#include <stdio.h>
#include <json-c/json.h>

json_object* createJson(int x1, int y1, int attack1, int vision1, int x2, int y2, int attack2, int vision2) {

   /*Creating a json object*/
   json_object * jobj = json_object_new_object();

/*Creating a json object*/

    json_object * jobj1 = json_object_new_object();

/*Creating a json integer*/

    json_object *jint1 = json_object_new_int(attack1);

  /*Form the json object*/

    json_object_object_add(jobj1,"at", jint1);

  /*Creating a json object*/

    json_object * jobj2 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint2 = json_object_new_int(x1);

  /*Form the json object*/

    json_object_object_add(jobj2,"equis", jint1);

  /*Creating a json object*/

    json_object * jobj3 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint3 = json_object_new_int(y1);

  /*Form the json object*/

    json_object_object_add(jobj3,"ye", jint1);

  /*Creating a json object*/

    json_object * jobj4 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint4 = json_object_new_int(vision1);

  /*Form the json object*/

    json_object_object_add(jobj4,"vis", jint1);


  /*Creating a json array*/

    json_object *jarray1 = json_object_new_array();

  /*Creating json strings*/

    json_object *jstring1 = json_object_new_string(json_object_to_json_string(jobj1));
    json_object *jstring2 = json_object_new_string(json_object_to_json_string(jobj2));
    json_object *jstring3 = json_object_new_string(json_object_to_json_string(jobj3));
    json_object *jstring4 = json_object_new_string(json_object_to_json_string(jobj4));

  /*Adding the above created json strings to the array*/

    json_object_array_add(jarray1,jstring1);
    json_object_array_add(jarray1,jstring2);
    json_object_array_add(jarray1,jstring3);
    json_object_array_add(jarray1,jstring4);


  /*Creating a json object*/

    json_object * jobj5 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint5 = json_object_new_int(attack2);

  /*Form the json object*/

    json_object_object_add(jobj5,"at", jint5);

  /*Creating a json object*/

    json_object * jobj6 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint6 = json_object_new_int(x2);

  /*Form the json object*/

    json_object_object_add(jobj6,"equis", jint6);

  /*Creating a json object*/

    json_object * jobj7 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint7 = json_object_new_int(y2);

  /*Form the json object*/

    json_object_object_add(jobj7,"ye", jint7);

  /*Creating a json object*/

    json_object * jobj8 = json_object_new_object();

  /*Creating a json integer*/

    json_object *jint8 = json_object_new_int(vision2);

  /*Form the json object*/

    json_object_object_add(jobj8,"vis", jint8);

    json_object *jarray2 = json_object_new_array();


  /*Creating json strings*/

    json_object *jstring5 = json_object_new_string(json_object_to_json_string(jobj5));
    json_object *jstring6 = json_object_new_string(json_object_to_json_string(jobj6));
    json_object *jstring7 = json_object_new_string(json_object_to_json_string(jobj7));
    json_object *jstring8 = json_object_new_string(json_object_to_json_string(jobj8));

  /*Adding the above created json strings to the array*/

    json_object_array_add(jarray2,jstring5);
    json_object_array_add(jarray2,jstring6);
    json_object_array_add(jarray2,jstring7);
    json_object_array_add(jarray2,jstring8);

    json_object_object_add(jobj, "4", jarray1);
    json_object_object_add(jobj, "5", jarray2);

    return jobj;

}
