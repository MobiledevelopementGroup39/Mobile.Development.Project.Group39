from django import forms
from .models import ImagePost, ImageComment

class ImageUploadForm(forms.ModelForm):
    class Meta:
        model = ImagePost
        fields = ['title', 'image']
        

class ImageCommentForm(forms.ModelForm):
    class Meta:
        model = ImageComment
        fields = ['text']

