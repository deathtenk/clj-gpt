# Clojure ChatGPT Client

This is a Clojure-based client for interacting with ChatGPT, a large language model trained by OpenAI.

## Dependencies

This project uses the following dependencies:

- [clj-http](https://github.com/dakrone/clj-http) - for making HTTP requests
- [cheshire](https://github.com/dakrone/cheshire) - for parsing JSON responses

## Configuration

Before using the client, you will need to obtain an API key from OpenAI. You can do this by creating an account on the OpenAI Developer API page. Once you have your API key, you can store it in a `resources/config.edn` file, like this:

```clojure
{:api-key "your-api-key"}
```

Replace the value with your own API key.

## Usage

To use the client, you can require the clj-gpt.client namespace and call the functions defined in the namespace. Here's an example of how to use the client:

```clojure
(ns myapp.core
  (:require [chatgpt.client :as chat]))

(defn chat-example [prompt]
  (let [response (chat/generate-response prompt)]
    (println (-> response
                 first
                 :message
                 :content))))

(chat-example "hello there!") ; => "Hello! How can I assist you today?"
```

In this example, chat/generate-response is used to generate a response from ChatGPT given a prompt. The response is returned as a parsed JSON object with the generated text packed in a list, each with messages corresponding to the different responses that get generated.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Acknowledgements

This project follows the [OpenAI API documentation](https://beta.openai.com/docs/api-reference/introduction).
