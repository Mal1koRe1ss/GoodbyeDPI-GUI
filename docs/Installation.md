# Kurulum

**Öncelikle Source Code'ı indirin.**

![image](https://github.com/user-attachments/assets/e9c7f99e-ebdb-430b-b803-fbf64bf83b84)

**İndirdiğiniz dosyayı açın.**

![image](https://github.com/user-attachments/assets/030e798c-0fd8-45f0-9614-f178263cde90)

**İçindeki klasörü bir dizine çıkartın.**

![image](https://github.com/user-attachments/assets/eb739985-d0de-4e8e-a0c1-00e45d7cbc60)

**O dizinde powershell'i çalıştırın.**

![image](https://github.com/user-attachments/assets/3d5b7072-b809-4372-a0dd-6f65c6edf44f)

**Aşağıdaki scripti çalıştırın**

### NOT : Java için lütfen [Adoptium OpenJDK](<https://adoptium.net/>) kullanın. 
### NOT 2 : Eğerki Java'nın yerini değiştirdiyseniz, yeri değiştirip scripti o şekilde çalıştırın.

```postscript
$currentDir = $pwd.Path; & 'C:\Program Files\Eclipse Adoptium\jdk-21.0.6.7-hotspot\bin\java.exe' -XX:+ShowCodeDetailsInExceptionMessages -cp "$currentDir\out;$currentDir\lib\flatlaf-3.5.4.jar" 'Main'
```

**Bitiş artık kullanabilirsiniz.** 🎉

![image](https://github.com/user-attachments/assets/ec4130a8-75f4-4a5f-8690-5beaba09ccfa)
