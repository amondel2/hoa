databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1590646010457-1") {
        createTable(tableName: "acl_class") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "class", type: "VARCHAR(255)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-2") {
        createTable(tableName: "acl_entry") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "ace_order", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "acl_object_identity", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "audit_failure", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "audit_success", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "granting", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "mask", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "sid", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-3") {
        createTable(tableName: "acl_object_identity") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "object_id_class", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "entries_inheriting", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "object_id_identity", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "owner_sid", type: "BIGINT")

            column(defaultValueComputed: "NULL", name: "parent_object", type: "BIGINT")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-4") {
        createTable(tableName: "acl_sid") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "principal", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "sid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-5") {
        createTable(tableName: "bank") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(defaultValue: "t", name: "name", type: "VARCHAR(256)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-6") {
        createTable(tableName: "capital_contrib") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "due_date", type: "datetime") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-7") {
        createTable(tableName: "due_months") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "end_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "start_date", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-8") {
        createTable(tableName: "due_months_due_months") {
            column(name: "due_months_due_months_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "due_months_id", type: "BIGINT")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-9") {
        createTable(tableName: "expense_type") {
            column(autoIncrement: "true", name: "id", type: "INT UNSIGNED") {
                constraints(primaryKey: "true")
            }

            column(name: "type", type: "VARCHAR(45)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-10") {
        createTable(tableName: "fee") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "assessment_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "due_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "feetype", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "house_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "paid_date", type: "datetime")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-11") {
        createTable(tableName: "house") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address1", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "address2", type: "VARCHAR(255)")

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "state", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "zip1", type: "INT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "zip2", type: "INT")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-12") {
        createTable(tableName: "house_capital") {
            column(name: "cap_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "house_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "paid", type: "BIT(1)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-13") {
        createTable(tableName: "house_car") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "house_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "license_plate", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "is_owned_by_house", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "make", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "color", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "model", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-14") {
        createTable(tableName: "house_month") {
            column(name: "months_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "house_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "paid", type: "BIT(1)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-15") {
        createTable(tableName: "meeting_minutes") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "minutes", type: "LONGTEXT") {
                constraints(nullable: "false")
            }

            column(name: "meet_date", type: "datetime") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-16") {
        createTable(tableName: "mess_role") {
            column(name: "mess_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-17") {
        createTable(tableName: "messages") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "expire_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "order_number", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "text", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-18") {
        createTable(tableName: "parking") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "spot_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-19") {
        createTable(tableName: "parking_spot_reservation") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "parking_spot_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "car_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-20") {
        createTable(tableName: "profile") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "first_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "phone_number", type: "VARCHAR(255)")

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "answer1", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "answer2", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "question1", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(name: "question2", type: "VARCHAR(300)") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "home_id", type: "BIGINT")

            column(defaultValueBoolean: "false", name: "show_address_info", type: "BIT(3)")

            column(defaultValueBoolean: "false", name: "show_email_info", type: "BIT(3)")

            column(defaultValueBoolean: "false", name: "show_Phone", type: "BIT(3)")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-21") {
        createTable(tableName: "registration_code") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "date_created", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-22") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-23") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BIT(1)") {
                constraints(nullable: "false")
            }

            column(name: "username", type: "VARCHAR(255)") {
                constraints(nullable: "false", unique: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-24") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-25") {
        createTable(tableName: "vendor") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address1", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "address2", type: "VARCHAR(255)")

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "email", type: "VARCHAR(255)")

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false", unique: "true")
            }

            column(name: "phone", type: "VARCHAR(255)")

            column(name: "state", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "zip1", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "account_Number", type: "VARCHAR(255)")

            column(defaultValueNumeric: "1", name: "is_active", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-26") {
        createTable(tableName: "vendor_fin") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "amount", type: "DECIMAL(19, 2)") {
                constraints(nullable: "false")
            }

            column(name: "due_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "paid_date", type: "datetime")

            column(name: "vendor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(defaultValueComputed: "NULL", name: "expense_type", type: "INT")

            column(name: "expense_type_id", type: "VARCHAR(45)")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-27") {
        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK2fb6368fc1291102ed8f8aedfb3f", tableName: "house")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-28") {
        addUniqueConstraint(columnNames: "amount, start_date", constraintName: "UK37ea585ebf33c1fd375557cf144d", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-29") {
        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "UK50968f3149349bb5bd39335b39c5", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-30") {
        addUniqueConstraint(columnNames: "amount, end_date", constraintName: "UK59967b108dc4d4ea93cc6b49379b", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-31") {
        addUniqueConstraint(columnNames: "amount, end_date", constraintName: "idx_due_months_amount_end_date", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-32") {
        addUniqueConstraint(columnNames: "amount, start_date", constraintName: "idx_due_months_amount_start_date", tableName: "due_months")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-33") {
        addUniqueConstraint(columnNames: "acl_object_identity, ace_order", constraintName: "unique_ace_order", tableName: "acl_entry")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-34") {
        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "unique_address1", tableName: "house")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-35") {
        addUniqueConstraint(columnNames: "state, city, address2, address1", constraintName: "unique_address1", tableName: "vendor")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-36") {
        addUniqueConstraint(columnNames: "object_id_class, object_id_identity", constraintName: "unique_object_id_identity", tableName: "acl_object_identity")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-37") {
        addUniqueConstraint(columnNames: "sid, principal", constraintName: "unique_principal", tableName: "acl_sid")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-38") {
        createIndex(indexName: "FK48c0xnfwcwniptexo8q9mqse2", tableName: "due_months_due_months") {
            column(name: "due_months_due_months_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-39") {
        createIndex(indexName: "FK7vh68atm50in5fi6cfjtmrrt9", tableName: "parking_spot_reservation") {
            column(name: "parking_spot_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-40") {
        createIndex(indexName: "FK_1nr5wythuuobda16hm1sv0hqi", tableName: "fee") {
            column(name: "house_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-41") {
        createIndex(indexName: "FK_6oap2k8q5bl33yq3yffrwedhf", tableName: "acl_object_identity") {
            column(defaultValueComputed: "NULL", name: "parent_object")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-42") {
        createIndex(indexName: "FK_94nmgc5csoheb4ne798bd0piv", tableName: "house_capital") {
            column(name: "house_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-43") {
        createIndex(indexName: "FK_ajbh0fer1gjrwxdgs9g3p0aw9", tableName: "house_month") {
            column(name: "house_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-44") {
        createIndex(indexName: "FK_c1dkiawnlj6uoe6fnlwd6j83j", tableName: "profile") {
            column(name: "user_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-45") {
        createIndex(indexName: "FK_cldb9ldbhuysilqjxq4ao3ooj", tableName: "mess_role") {
            column(name: "role_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-46") {
        createIndex(indexName: "FK_epn96tbihdx6hjqbm7smdlx9p", tableName: "vendor_fin") {
            column(name: "vendor_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-47") {
        createIndex(indexName: "FK_i4ej6hxjfn34307t8l0mvlj1o", tableName: "profile") {
            column(defaultValueComputed: "NULL", name: "home_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-48") {
        createIndex(indexName: "FK_i6xyfccd4y3wlwhgwpo4a9rm1", tableName: "acl_entry") {
            column(name: "sid")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-49") {
        createIndex(indexName: "FK_it77eq964jhfqtu54081ebtio", tableName: "user_role") {
            column(name: "role_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-50") {
        createIndex(indexName: "FK_nxv5we2ion9fwedbkge7syoc3", tableName: "acl_object_identity") {
            column(defaultValueComputed: "NULL", name: "owner_sid")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-51") {
        createIndex(indexName: "FKcu703n9w202jehuk5paoygij6", tableName: "parking_spot_reservation") {
            column(name: "car_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-52") {
        createIndex(indexName: "FKjg3xuunf6tx92gf1vtqtwfvxh", tableName: "due_months_due_months") {
            column(defaultValueComputed: "NULL", name: "due_months_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-53") {
        createIndex(indexName: "FKpfoqoov2vucgv30xl9d6osydm", tableName: "house_car") {
            column(name: "house_id")
        }
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-54") {
        addForeignKeyConstraint(baseColumnNames: "due_months_due_months_id", baseTableName: "due_months_due_months", constraintName: "FK48c0xnfwcwniptexo8q9mqse2", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "due_months", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-55") {
        addForeignKeyConstraint(baseColumnNames: "home_id", baseTableName: "profile", constraintName: "FK7pujn8l9ev9htqfabn51elfxv", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-56") {
        addForeignKeyConstraint(baseColumnNames: "parking_spot_id", baseTableName: "parking_spot_reservation", constraintName: "FK7vh68atm50in5fi6cfjtmrrt9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "parking", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-57") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-58") {
        addForeignKeyConstraint(baseColumnNames: "house_id", baseTableName: "house_month", constraintName: "FK8v2q7cp8q4c3p93rfj7pjp8mq", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-59") {
        addForeignKeyConstraint(baseColumnNames: "house_id", baseTableName: "fee", constraintName: "FK_1nr5wythuuobda16hm1sv0hqi", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-60") {
        addForeignKeyConstraint(baseColumnNames: "mess_id", baseTableName: "mess_role", constraintName: "FK_3odjwq4c4hbtub3t23u8hfyx7", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "messages", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-61") {
        addForeignKeyConstraint(baseColumnNames: "object_id_class", baseTableName: "acl_object_identity", constraintName: "FK_6c3ugmk053uy27bk2sred31lf", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "acl_class", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-62") {
        addForeignKeyConstraint(baseColumnNames: "parent_object", baseTableName: "acl_object_identity", constraintName: "FK_6oap2k8q5bl33yq3yffrwedhf", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "acl_object_identity", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-63") {
        addForeignKeyConstraint(baseColumnNames: "cap_id", baseTableName: "house_capital", constraintName: "FK_7rthr5y3f5wxyaaj1wfy97lht", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "capital_contrib", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-64") {
        addForeignKeyConstraint(baseColumnNames: "house_id", baseTableName: "house_capital", constraintName: "FK_94nmgc5csoheb4ne798bd0piv", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-65") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "mess_role", constraintName: "FK_cldb9ldbhuysilqjxq4ao3ooj", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-66") {
        addForeignKeyConstraint(baseColumnNames: "vendor_id", baseTableName: "vendor_fin", constraintName: "FK_epn96tbihdx6hjqbm7smdlx9p", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "vendor", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-67") {
        addForeignKeyConstraint(baseColumnNames: "acl_object_identity", baseTableName: "acl_entry", constraintName: "FK_fhuoesmjef3mrv0gpja4shvcr", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "acl_object_identity", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-68") {
        addForeignKeyConstraint(baseColumnNames: "sid", baseTableName: "acl_entry", constraintName: "FK_i6xyfccd4y3wlwhgwpo4a9rm1", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "acl_sid", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-69") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK_it77eq964jhfqtu54081ebtio", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "role", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-70") {
        addForeignKeyConstraint(baseColumnNames: "months_id", baseTableName: "house_month", constraintName: "FK_m964lyfbsh0yampfuopcvhdsg", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "due_months", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-71") {
        addForeignKeyConstraint(baseColumnNames: "owner_sid", baseTableName: "acl_object_identity", constraintName: "FK_nxv5we2ion9fwedbkge7syoc3", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "acl_sid", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-72") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "profile", constraintName: "FKawh070wpue34wqvytjqr4hj5e", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "user", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-73") {
        addForeignKeyConstraint(baseColumnNames: "car_id", baseTableName: "parking_spot_reservation", constraintName: "FKcu703n9w202jehuk5paoygij6", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house_car", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-74") {
        addForeignKeyConstraint(baseColumnNames: "due_months_id", baseTableName: "due_months_due_months", constraintName: "FKjg3xuunf6tx92gf1vtqtwfvxh", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "due_months", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1590646010457-75") {
        addForeignKeyConstraint(baseColumnNames: "house_id", baseTableName: "house_car", constraintName: "FKpfoqoov2vucgv30xl9d6osydm", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "id", referencedTableName: "house", validate: "true")
    }
    include file: 'user.groovy'
    include file: 'user2.groovy'
    include file: 'user3.groovy'
    include file: 'user4.groovy'
}
