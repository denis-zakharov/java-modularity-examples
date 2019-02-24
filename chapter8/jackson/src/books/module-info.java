module books {
    requires jackson.databind;

    opens demo to jackson.databind;
}
