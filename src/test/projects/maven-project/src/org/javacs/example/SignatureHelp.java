/************************************************************************************
 * This file is part of Java Language Server (https://github.com/itsaky/java-language-server)
 *
 * Copyright (C) 2021 Akash Yadav
 *
 * Java Language Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Java Language Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Java Language Server.  If not, see <https://www.gnu.org/licenses/>.
 *
**************************************************************************************/

package org.javacs.example;

import java.util.concurrent.CompletableFuture; import java.util.ArrayList;

class SignatureHelp {
    void test(Runnable r) {
        CompletableFuture.runAsync();
        CompletableFuture.runAsync(r, );
        new SignatureHelp();
        new ArrayList<>(1);
    }

    /**
     * A constructor
     */
    SignatureHelp(String name) {
        // Nothing to do
    }
}