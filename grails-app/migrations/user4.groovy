databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1597030756016-1") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "rest_token", tableName: "user")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-2") {
        dropUniqueConstraint(constraintName: "UC_PROFILEID_COL", tableName: "profile")

        addUniqueConstraint(columnNames: "id", constraintName: "UC_PROFILEID_COL", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-3") {
        dropUniqueConstraint(constraintName: "UC_PROFILEUSER_ID_COL", tableName: "profile")

        addUniqueConstraint(columnNames: "user_id", constraintName: "UC_PROFILEUSER_ID_COL", tableName: "profile")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-4") {
        dropUniqueConstraint(constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")

        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-5") {
        dropUniqueConstraint(constraintName: "UC_USERUSERNAME_COL", tableName: "user")

        addUniqueConstraint(columnNames: "username", constraintName: "UC_USERUSERNAME_COL", tableName: "user")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-6") {
        dropUniqueConstraint(constraintName: "UC_VENDORNAME_COL", tableName: "vendor")

        addUniqueConstraint(columnNames: "name", constraintName: "UC_VENDORNAME_COL", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-7") {
        dropUniqueConstraint(constraintName: "UK2fb6368fc1291102ed8f8aedfb3f", tableName: "house")

        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK2fb6368fc1291102ed8f8aedfb3f", tableName: "house")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-8") {
        dropUniqueConstraint(constraintName: "UK37ea585ebf33c1fd375557cf144d", tableName: "due_months")

        addUniqueConstraint(columnNames: "amount, start_date", constraintName: "UK37ea585ebf33c1fd375557cf144d", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-9") {
        dropUniqueConstraint(constraintName: "UK50968f3149349bb5bd39335b39c5", tableName: "vendor")

        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK50968f3149349bb5bd39335b39c5", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1597030756016-10") {
        dropUniqueConstraint(constraintName: "UK59967b108dc4d4ea93cc6b49379b", tableName: "due_months")

        addUniqueConstraint(columnNames: "amount, end_date", constraintName: "UK59967b108dc4d4ea93cc6b49379b", tableName: "due_months")
    }
}
