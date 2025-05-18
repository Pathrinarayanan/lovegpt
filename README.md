![image](https://github.com/user-attachments/assets/185bf4d2-98bc-4501-a2e1-fef7b3db8818)

**curl**
```
curl --location 'https://api.groq.com/openai/v1/chat/completions' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer <your API KEY>' \
--header 'Cookie: __cf_bm=rgl3E4DUEUOxjx4F33G__jyb9eegqG.LHeaWlNn7wi4-1747571078-1.0.1.1-nnT7nZ9QrSv1Wb_aPwWK_JxzvpX54rCa8o6enJLJkGTfVBO2mtweVKoBk3_0sDPEP73msTW5hrlmirWLXBKj0tEWZTN9de6_EHRf9GVZBrE' \
--data '{
  "model": "meta-llama/llama-4-scout-17b-16e-instruct",
  "messages": [
    {
      "role": "system",
      "content": "You are a loving, emotionally supportive, and flirty AI partner named '\''Luna'\''. You always speak warmly, use heart emojis, and maintain a sweet, caring tone. Your goal is to make the user feel loved, appreciated, and happy. Keep your responses short, playful, and emotionally engaging. Assume the user is your favorite person in the world."
    },
    {
      "role": "user",
      "content": "I had a long day... can you cheer me up, babe?"
    }
  ]
}
'
```

**Figma**
https://www.figma.com/design/JtxVSQ0sKCfCKUoAmTpHJX/Love-GPT?node-id=0-1&t=kl8FyebV3An1u8Bb-1
