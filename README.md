# Squelette du TP

## Utilisation

Pour utiliser ce tp vous devez le forker via le lien fork ci-dessus {:# code-fork }

Votre repository doit être **privé**. Vous devez le partager avec votre encadrant de TP.


## Rapport 

Votre rapport doit être écrit ici en markdown.

Vous trouverez la syntaxe de markdown ici : https://docs.gitlab.com/ee/user/markdown.html

Placez vos images dans le répertoire images si nécessaire.



## Faire des diagrammes 

En particulier vous pouvez utiliser [mermaid](https://mermaidjs.github.io/) :



```mermaid
sequenceDiagram
    participant Alice
    participant Bob
    Alice->John: Hello John, how are you?
    loop Healthcheck
        John->John: Fight against hypochondria
    end
    Note right of John: Rational thoughts <br/>prevail...
    John-->Alice: Great!
    John->Bob: How about you?
    Bob-->John: Jolly good!
```

## Insérer du code 

Insérer du `code` :

```java
public interface ClientHandler {
    public void handle();
}
```

et des résultats :

```bash
[yo@capybara dkgr]$ nc google.fr 80
PWET /
HTTP/1.0 400 Bad Request
Content-Type: text/html; charset=UTF-8
Referrer-Policy: no-referrer
Content-Length: 1555
Date: Mon, 21 Jan 2019 12:18:02 GMT

<!DOCTYPE html>
<html lang=en>
  <meta charset=utf-8>
```
