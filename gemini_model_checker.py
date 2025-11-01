import google.generativeai as genai
from dotenv import load_dotenv
import os

# Load environment variables
load_dotenv()
api_key = os.getenv("GEMINI_API_KEY")

if not api_key:
    print("ERROR: GEMINI_API_KEY not found in .env file.")
    exit(1)

# Configure the API
genai.configure(api_key=api_key)

print("=" * 60)
print("GEMINI API DIAGNOSTIC TOOL")
print("=" * 60)

# List all available models
print("\n📋 Listing all available models:\n")
try:
    models = genai.list_models()
    
    generate_content_models = []
    for model in models:
        print(f"Model Name: {model.name}")
        print(f"  Display Name: {model.display_name}")
        print(f"  Description: {model.description}")
        print(f"  Supported Methods: {model.supported_generation_methods}")
        print()
        
        # Check if generateContent is supported
        if 'generateContent' in model.supported_generation_methods:
            generate_content_models.append(model.name)
    
    print("=" * 60)
    print("\n✅ Models that support 'generateContent':")
    for model_name in generate_content_models:
        print(f"  - {model_name}")
    
    print("\n" + "=" * 60)
    print("\n🧪 Testing a simple generation with the first available model...")
    
    if generate_content_models:
        test_model_name = generate_content_models[0]
        print(f"Testing with: {test_model_name}")
        
        try:
            model = genai.GenerativeModel(test_model_name)
            response = model.generate_content("Say hello in one word")
            print(f"✅ Success! Response: {response.text}")
        except Exception as e:
            print(f"❌ Failed: {e}")
    else:
        print("❌ No models found that support generateContent")
        
except Exception as e:
    print(f"❌ Error listing models: {e}")
    print("\nTry updating the package: pip install --upgrade google-generativeai")

print("\n" + "=" * 60)
print("Package version info:")
print(f"google-generativeai version: {genai.__version__}")
print("=" * 60)
