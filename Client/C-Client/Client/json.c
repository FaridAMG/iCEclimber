#include <json-c/json.h>
#include <stdio.h>

char str[4];

json_object* createNewJson(int player, int x, int y, int attack, int vision) {

    char formatedPlayer[4];

    snprintf(str, player, "%s", formatedPlayer);


  /*Creating a json object*/
  json_object * jobj1 = json_object_new_object();
  /*Creating a json integer*/
  json_object *jint1 = json_object_new_int(attack);
  /*Form the json object*/
  json_object_object_add(jobj1,"at", jint1);
/*Creating a json object*/
  json_object * jobj2 = json_object_new_object();
  /*Creating a json integer*/
  json_object *jint2 = json_object_new_int(x);
  /*Form the json object*/
  json_object_object_add(jobj2,"equis", jint1);
/*Creating a json object*/
  json_object * jobj3 = json_object_new_object();
  /*Creating a json integer*/
  json_object *jint3 = json_object_new_int(y);
  /*Form the json object*/
  json_object_object_add(jobj3,"ye", jint1);
/*Creating a json object*/
  json_object * jobj4 = json_object_new_object();
  /*Creating a json integer*/
  json_object *jint4 = json_object_new_int(vision);
  /*Form the json object*/
  json_object_object_add(jobj4,"vis", jint1);
  /*Creating a json object*/
  json_object * jobj = json_object_new_object();
  /*Creating a json array*/
  json_object *jarray = json_object_new_array();
  /*Creating json strings*/
  json_object *jstring1 = json_object_new_string(json_object_to_json_string(jobj1));
  json_object *jstring2 = json_object_new_string(json_object_to_json_string(jobj2));
  json_object *jstring3 = json_object_new_string(json_object_to_json_string(jobj3));
json_object *jstring4 = json_object_new_string(json_object_to_json_string(jobj4));
  /*Adding the above created json strings to the array*/
  json_object_array_add(jarray,jstring1);
  json_object_array_add(jarray,jstring2);
  json_object_array_add(jarray,jstring3);
json_object_array_add(jarray,jstring4);
  /*Form the json object*/
  json_object_object_add(jobj, formatedPlayer, jarray);
}