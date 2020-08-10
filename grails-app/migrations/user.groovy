databaseChangeLog = {

   changeSet(author: "aaron (generated)", id: "1590646534944-19") {
        addColumn(tableName: "user") {
            column(name: "rest_token", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-21") {
        addUniqueConstraint(columnNames: "id", constraintName: "UC_PROFILEID_COL", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-22") {
        addUniqueConstraint(columnNames: "user_id", constraintName: "UC_PROFILEUSER_ID_COL", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-23") {
        dropForeignKeyConstraint(baseTableName: "mess_role", constraintName: "FK_3odjwq4c4hbtub3t23u8hfyx7")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-24") {
        dropForeignKeyConstraint(baseTableName: "acl_object_identity", constraintName: "FK_6c3ugmk053uy27bk2sred31lf")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-25") {
        dropForeignKeyConstraint(baseTableName: "acl_object_identity", constraintName: "FK_6oap2k8q5bl33yq3yffrwedhf")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-26") {
        dropForeignKeyConstraint(baseTableName: "house_capital", constraintName: "FK_7rthr5y3f5wxyaaj1wfy97lht")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-27") {
        dropForeignKeyConstraint(baseTableName: "house_capital", constraintName: "FK_94nmgc5csoheb4ne798bd0piv")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-28") {
        dropForeignKeyConstraint(baseTableName: "mess_role", constraintName: "FK_cldb9ldbhuysilqjxq4ao3ooj")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-29") {
        dropForeignKeyConstraint(baseTableName: "acl_entry", constraintName: "FK_fhuoesmjef3mrv0gpja4shvcr")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-30") {
        dropForeignKeyConstraint(baseTableName: "acl_entry", constraintName: "FK_i6xyfccd4y3wlwhgwpo4a9rm1")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-31") {
        dropForeignKeyConstraint(baseTableName: "acl_object_identity", constraintName: "FK_nxv5we2ion9fwedbkge7syoc3")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-32") {
        dropUniqueConstraint(constraintName: "UK_979qjtucxtkiu3dpqa18owsh7", tableName: "capital_contrib")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-33") {
        dropUniqueConstraint(constraintName: "UK_iy7ua5fso3il3u3ymoc4uf35w", tableName: "acl_class")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-34") {
        dropUniqueConstraint(constraintName: "type_UNIQUE", tableName: "expense_type")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-35") {
        dropUniqueConstraint(constraintName: "unique_ace_order", tableName: "acl_entry")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-36") {
        dropUniqueConstraint(constraintName: "unique_object_id_identity", tableName: "acl_object_identity")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-37") {
        dropUniqueConstraint(constraintName: "unique_principal", tableName: "acl_sid")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-38") {
        dropTable(tableName: "acl_class")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-39") {
        dropTable(tableName: "acl_entry")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-40") {
        dropTable(tableName: "acl_object_identity")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-41") {
        dropTable(tableName: "acl_sid")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-42") {
        dropTable(tableName: "capital_contrib")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-43") {
        dropTable(tableName: "house_capital")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-44") {
        dropTable(tableName: "mess_role")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-45") {
        dropColumn(columnName: "expense_type", tableName: "vendor_fin")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-46") {
        dropColumn(columnName: "expense_type_id", tableName: "vendor_fin")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-47") {
        dropColumn(columnName: "version", tableName: "user_role")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-1") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "address2", tableName: "house")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-2") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "address2", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-3") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "email", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-4") {
        dropDefaultValue(columnDataType: "boolean", columnName: "is_active", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-5") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "name", tableName: "bank")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-6") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "phone", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-7") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "phone_number", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-8") {
        dropDefaultValue(columnDataType: "boolean", columnName: "show_address_info", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-9") {
        dropDefaultValue(columnDataType: "boolean", columnName: "show_email_info", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-11") {

        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-12") {


        addUniqueConstraint(columnNames: "name", constraintName: "UC_VENDORNAME_COL", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-13") {
        dropUniqueConstraint(constraintName: "UK2fb6368fc1291102ed8f8aedfb3f", tableName: "house")

        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK2fb6368fc1291102ed8f8aedfb3f", tableName: "house")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-14") {
        dropUniqueConstraint(constraintName: "UK37ea585ebf33c1fd375557cf144d", tableName: "due_months")

        addUniqueConstraint(columnNames: "amount, start_date", constraintName: "UK37ea585ebf33c1fd375557cf144d", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-15") {
        dropUniqueConstraint(constraintName: "UK50968f3149349bb5bd39335b39c5", tableName: "vendor")

        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK50968f3149349bb5bd39335b39c5", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-16") {
        dropUniqueConstraint(constraintName: "UK59967b108dc4d4ea93cc6b49379b", tableName: "due_months")

        addUniqueConstraint(columnNames: "amount, end_date", constraintName: "UK59967b108dc4d4ea93cc6b49379b", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646534944-17") {
        dropUniqueConstraint(constraintName: "UC_PROFILEUSER_ID_COL", tableName: "profile")

        addUniqueConstraint(columnNames: "user_id", constraintName: "UC_PROFILEUSER_ID_COL", tableName: "profile")
    }
}
