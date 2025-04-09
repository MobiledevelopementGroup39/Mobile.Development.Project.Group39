from .models import VideoComment
from django import forms
from .models import Video

class VideoCommentForm(forms.ModelForm):
    class Meta:
        model = VideoComment
        fields = ['text']


class VideoUploadForm(forms.ModelForm):
    class Meta:
        model = Video
        fields = ['title', 'description', 'video_file']
