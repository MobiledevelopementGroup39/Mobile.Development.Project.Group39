from rest_framework import serializers
from .models import Video, VideoComment

class VideoSerializer(serializers.ModelSerializer):
    uploaded_by = serializers.CharField(source='uploaded_by.username', read_only=True)
    total_likes = serializers.ReadOnlyField()
    total_dislikes = serializers.ReadOnlyField()

    class Meta:
        model = Video
        fields = ['id', 'title', 'description', 'video_file', 'uploaded_by', 'uploaded_at', 'total_likes', 'total_dislikes', 'thamnail']


class VideoCommentSerializer(serializers.ModelSerializer):
    user = serializers.CharField(source='user.username', read_only=True)  # This line does the trick

    class Meta:
        model = VideoComment
        fields = ['id', 'video', 'user', 'text', 'created_at']
