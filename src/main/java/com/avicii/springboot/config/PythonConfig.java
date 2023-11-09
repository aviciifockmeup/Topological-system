package com.avicii.springboot.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonConfig {
    static  String pyPath="D:\\TOOL\\Miniconda\\python.exe";

    public static int accessPy(int nodeNum, int edgeNum) throws InterruptedException, IOException {
        int exitCode = 0;
        try {
            // 构建 Python 命令及参数
            String pythonScript = "D:\\Projects\\SystemA\\springboot\\springboot\\src\\main\\python\\createGraph1.py";
            ProcessBuilder processBuilder =new ProcessBuilder(pyPath, pythonScript, String.valueOf(nodeNum), String.valueOf(edgeNum));
            // 启动进程并执行命令
            Process process =processBuilder.start();
            // 获取命令输出
            BufferedReader reader =new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder output =new StringBuilder();
            StringBuilder errorOutput = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            exitCode = process.waitFor();
            // 打印标准输出和标准错误输出
            System.out.println("Standard Output: " + output.toString());
            System.out.println("Error Output: " + errorOutput.toString());
            System.out.println("Exit Code: " + exitCode);

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
        return exitCode;
    }

    public static String getPyResult(int nodeNum, int edgeNum) throws InterruptedException, IOException {
        String outputString = null;
        int exitCode = 0;
        try {
            // 构建 Python 命令及参数
            String pythonScript = "D:\\Projects\\SystemA\\springboot\\springboot\\src\\main\\python\\createGraph1.py";
            ProcessBuilder processBuilder =new ProcessBuilder(pyPath, pythonScript, String.valueOf(nodeNum), String.valueOf(edgeNum));
            // 启动进程并执行命令
            Process process =processBuilder.start();
            // 获取命令输出
            BufferedReader reader =new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            StringBuilder output =new StringBuilder();
            StringBuilder errorOutput = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line);
            }
            while ((line = errorReader.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }
            exitCode = process.waitFor();
            // 打印标准输出和标准错误输出
            System.out.println("Standard Output: " + output.toString());
            System.out.println("Error Output: " + errorOutput.toString());
            System.out.println("Exit Code: " + exitCode);

            outputString = output.toString();

        } catch (IOException | InterruptedException e){
            e.printStackTrace();
        }


        return outputString;
    }



}
